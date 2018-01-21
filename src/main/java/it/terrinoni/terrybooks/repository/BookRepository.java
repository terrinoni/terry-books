package it.terrinoni.terrybooks.repository;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:56.
 */

import it.terrinoni.terrybooks.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO
 */
public interface BookRepository extends MongoRepository<Book, String> {

}
