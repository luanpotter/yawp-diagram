package io.yawp.extras.diagram.util;

import io.yawp.commons.utils.ReflectionUtils;
import io.yawp.repository.IdRef;
import io.yawp.repository.LazyJson;
import io.yawp.repository.RepositoryFeatures;
import io.yawp.repository.annotations.ParentId;
import io.yawp.repository.models.FieldModel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static io.yawp.commons.utils.ReflectionUtils.getFieldsRecursively;

public class Generator {

    public static List<Model> generate(RepositoryFeatures features) {
        List<Model> ms = new ArrayList<>();
        for (Class<?> endpoint : features.getEndpointClazzes()) {
            String kind = name(features, endpoint);
            if (kind.startsWith("__") || kind.equals("diagram")) {
                continue;
            }
            Model m = new Model(kind);
            ms.add(m);
            for (Field field : getFieldsRecursively(endpoint)) {

                FieldModel fieldModel = new FieldModel(field);
                if (fieldModel.isId()) {
                    continue;
                }

                if (fieldModel.isIdRef()) {
                    Type type = field.getAnnotation(ParentId.class) != null ? Type.PARENT_ID : Type.ID;
                    Class<?> idRefEndpointClazz = ReflectionUtils.getIdRefEndpointClazz(field);
                    m.add(type, field.getName(), name(features, idRefEndpointClazz));
                }

                if (fieldModel.isListOfIds()) {
                    Class<?> idRefEndpointClazz = getNInnerClass(field.getGenericType(), 2);
                    m.add(Type.LIST_ID, field.getName(), name(features, idRefEndpointClazz));
                }

                if (LazyJson.class.isAssignableFrom(field.getType())) {
                    Class<?> clazz = ReflectionUtils.getListGenericType(field.getGenericType());
                    if (IdRef.class.isAssignableFrom(clazz)) {
                        Class<?> c = getNInnerClass(field.getGenericType(), 2);
                        m.add(Type.LAZY_ID, field.getName(), name(features, c));
                    } else if (List.class.isAssignableFrom(clazz)) {
                        java.lang.reflect.Type type = getNInnerType(field.getGenericType(), 2);
                        if (type instanceof ParameterizedType) {
                            Class<?> idOf = ReflectionUtils.getListGenericType(type);
                            m.add(Type.LAZY_LIST_ID, field.getName(), name(features, idOf));
                        } else {
                            Class<?> elOf = (Class) type;
                            m.add(Type.LAZY_LIST_MODEL, field.getName(), name(features, elOf));
                        }
                    } else if (features.getByClazz(clazz) != null) {
                        m.add(Type.LAZY_MODEL, field.getName(), name(features, clazz));
                    } else {
                        System.out.println("Unmapped LazyJson: " + clazz + " on field " + field.getName());
                    }
                }
            }
        }
        return ms;
    }

    private static String name(RepositoryFeatures features, Class<?> idRefEndpointClazz) {
        return features.getByClazz(idRefEndpointClazz).getEndpointKind();
    }

    private static Class<?> getNInnerClass(java.lang.reflect.Type t, int n) {
        return (Class) getNInnerType(t, n);
    }

    private static java.lang.reflect.Type getNInnerType(java.lang.reflect.Type t, int n) {
        if (n == 0) {
            return t;
        }

        return getNInnerType(ReflectionUtils.getGenericTypeArgumentAt(t, 0), n - 1);
    }
}
