package io.yawp.extras.diagram.model;

import io.yawp.commons.http.annotation.GET;
import io.yawp.extras.diagram.util.Generator;
import io.yawp.extras.diagram.util.GraphicalPrinter;
import io.yawp.extras.diagram.util.Model;
import io.yawp.repository.Yawp;
import io.yawp.repository.actions.Action;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagramAction extends Action<Diagram> {

    @GET("generate")
    public List<Model> generate() {
        return Generator.generate(Yawp.yawp.getFeatures());
    }

    @GET("graphic")
    public Map<String, String> graphic() {
        String image = DatatypeConverter.printBase64Binary(GraphicalPrinter.generate(generate()));
        return map("content", image);
    }

    private Map<String, String> map(String k, String v) {
        Map<String, String> r = new HashMap<>();
        r.put(k, v);
        return r;
    }
}
