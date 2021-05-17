package com.example.HSEHelloWorld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class HseHelloWorldApplication {

	private String state = "no_state";

	@Value("${config.source:unkown}")
	private String configVariable;

	@GetMapping("/config")
	public String getConfigSource(){

		return "Configuration Source: "+configVariable;

	}

	@GetMapping
	public String sayHello(){

		return "Hallo Esslingen, Summer Semester 2021 ("+state+")";

	}	

	@GetMapping("/otherEndpoint")
	public String sayHelloAgainAndAgain(){

		return "Hallo Esslingen, Summer Semester 2021 (very new feature)";

	}

	@PostMapping("/postSomething/{stringParam}")
	public String saySomethingWithInput(@PathVariable String stringParam){

		state = stringParam;
		return "I have received the beautiful String: "+stringParam;

	}

	public static void main(String[] args) {
		SpringApplication.run(HseHelloWorldApplication.class, args);
	}

}
