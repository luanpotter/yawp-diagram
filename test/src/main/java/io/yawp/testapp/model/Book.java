package io.yawp.testapp.model;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;

@Endpoint(path = "/books")
public class Book {

    @Id
    IdRef<Book> id;

}
