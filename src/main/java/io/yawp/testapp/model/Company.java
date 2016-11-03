package io.yawp.testapp.model;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;

@Endpoint(path = "/companies")
public class Company {

    @Id
    IdRef<Company> id;

    String name;

}
