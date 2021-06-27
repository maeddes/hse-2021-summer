package com.example.KubernetesDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KubernetesDemoApplication {

	@Value("${HOSTNAME}")
	String hostName;

	@GetMapping("/hello")
	String sayHello(){

		return hostName+" Hallo, HSE Students!";
	}
	
	@GetMapping("/crash")
	String doSomethingStupid(){

		// really cool code here
		//System.exit(1);
		return "magic";
	}


	public static void main(String[] args) {
		SpringApplication.run(KubernetesDemoApplication.class, args);
	}

}
