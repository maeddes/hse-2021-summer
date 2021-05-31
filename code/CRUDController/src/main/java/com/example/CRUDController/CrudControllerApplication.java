package com.example.CRUDController;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
public class CrudControllerApplication {

	ArrayList<String> listOfStrings = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(CrudControllerApplication.class);

	@GetMapping("/strings")
	public String readAllStrings(){

		logger.info("Strings being returned {}",listOfStrings);
		return listOfStrings.toString();

	}

	@PostMapping("/strings/{newString}")
	public String createString(@PathVariable String newString){

		logger.error("String to add: {}",newString);
		listOfStrings.add(newString);
		logger.debug("Current list of strings {}",listOfStrings);
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
		listOfStrings.remove(stringToDelete);
		logger.debug("Current list of strings {}",listOfStrings);
		return stringToDelete+" has most likely been removed from the list";

	}

	public static void main(String[] args) {
		SpringApplication.run(CrudControllerApplication.class, args);
	}

}
