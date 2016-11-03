package io.yawp.testapp.model;

import io.yawp.repository.IdRef;
import io.yawp.repository.LazyJson;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;

import java.util.List;

@Endpoint(path = "/admin")
public class Admin extends Person {

    @Id
    IdRef<Admin> id;

    LazyJson<List<User>> parents;

    LazyJson<List<IdRef<User>>> children;

    LazyJson<User> specialOther;

    LazyJson<IdRef<User>> specialOtherId;
}
