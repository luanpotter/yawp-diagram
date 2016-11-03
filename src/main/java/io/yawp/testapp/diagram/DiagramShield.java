package io.yawp.testapp.diagram;

import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.shields.Shield;

public class DiagramShield extends Shield<Diagram> {

    @Override
    public void always() {
        allow(false);
    }

    @GET("generate")
    public void generate() {
        allow();
    }
}
