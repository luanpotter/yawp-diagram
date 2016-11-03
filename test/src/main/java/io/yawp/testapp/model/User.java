package io.yawp.testapp.model;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;

import java.util.List;

@Endpoint(path = "/users")
public class User extends Person {

    @Id
    IdRef<User> id;

    List<IdRef<Book>> books;
}
