package com.finartz.repository;

import com.finartz.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends MongoRepository<Person, String> {
}
