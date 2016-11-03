package io.yawp.extras.diagram.util;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private String name;
    private List<Reference> refs;

    public Model(String name) {
        this.name = name;
        this.refs = new ArrayList<>();
    }

    public void add(Type t, String field, String model) {
        this.refs.add(new Reference(t, field, model));
    }
}
