package io.yawp.testapp.diagram;

import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.Yawp;
import io.yawp.repository.actions.Action;
import io.yawp.testapp.util.Generator;
import io.yawp.testapp.util.Model;

import java.util.List;

public class DiagramAction extends Action<Diagram> {

    @GET("generate")
    public List<Model> generate() {
        return Generator.generate(Yawp.yawp.getFeatures());
    }
}
