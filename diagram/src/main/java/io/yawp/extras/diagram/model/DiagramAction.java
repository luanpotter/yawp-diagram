package io.yawp.extras.diagram.model;

import io.yawp.commons.http.annotation.GET;
import io.yawp.extras.diagram.util.Generator;
import io.yawp.extras.diagram.util.Model;
import io.yawp.repository.Yawp;
import io.yawp.repository.actions.Action;

import java.util.List;

public class DiagramAction extends Action<Diagram> {

    @GET("generate")
    public List<Model> generate() {
        return Generator.generate(Yawp.yawp.getFeatures());
    }
}
