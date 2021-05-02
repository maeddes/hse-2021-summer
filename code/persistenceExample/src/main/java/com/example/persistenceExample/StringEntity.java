package com.example.persistenceExample;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StringEntity {

    @Id
    private String string;

    public StringEntity(){}

    public StringEntity(String string){

        this.string = string;
        
    }

    public String getString() {
        return string;
    }
    
}
