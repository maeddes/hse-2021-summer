package com.example.persistenceExample;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPAController {

    @Autowired
    private StringRepository stringRepository;
    private Logger logger = LoggerFactory.getLogger(JPAController.class);

    @GetMapping(path = "/strings", produces = "application/json")
	public String readAllStrings(){
	
        List<String> listOfStrings = new ArrayList<String>();

        //stringRepository.findAll().forEach(string -> listOfStrings.add(string.getString()));
        for(StringEntity string : stringRepository.findAll()) listOfStrings.add(string.getString());
        logger.info("Strings being returned {}",listOfStrings);

		return listOfStrings.toString();

	}

	@PostMapping("/strings/{newString}")
	public String createString(@PathVariable String newString){

		logger.error("String to add: {}",newString);
		
        stringRepository.save(new StringEntity(newString));
		//logger.debug("Current list of strings {}",listOfStrings);
		return newString+" has been added to the list";

	}

	@PutMapping("/strings/{oldString}/{newString}")
	public String updateString(@PathVariable String newString){

		// Todo :-)
		return "nothing happened with "+newString;

	}

	@DeleteMapping("/strings/{stringToDelete}")
	public String deleteString(@PathVariable String stringToDelete){

		logger.info("String to delete : {}",stringToDelete);
		//listOfStrings.remove(stringToDelete);
		//logger.debug("Current list of strings {}",listOfStrings);
		return stringToDelete+" has most likely been removed from the list";

	}

}
