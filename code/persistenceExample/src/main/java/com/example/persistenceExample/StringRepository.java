package com.example.persistenceExample;

import org.springframework.data.repository.CrudRepository;

public interface StringRepository extends CrudRepository<StringEntity, String>{
    
}
