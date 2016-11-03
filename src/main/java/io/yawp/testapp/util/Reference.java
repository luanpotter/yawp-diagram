package io.yawp.testapp.util;

public class Reference {

    String model;
    String field;
    Type type;

    public Reference(Type type, String field, String model) {
        this.type = type;
        this.field = field;
        this.model = model;
    }
}
