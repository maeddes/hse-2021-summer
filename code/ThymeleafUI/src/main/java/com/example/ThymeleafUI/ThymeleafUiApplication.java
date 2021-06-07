package com.example.ThymeleafUI;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class ThymeleafUiApplication {

	@Value("${welcome.recipient}")
	private String message;

	//private List<String> stringList = Arrays.asList("one","two","three");
	private ArrayList<String> stringList = new ArrayList<String>();

	@GetMapping("/")
	public String displayPage(Model model){

		model.addAttribute("message", message);
		model.addAttribute("list", stringList);

		return "page";
	}

	@GetMapping("/{name}")
	public String displayPage(@PathVariable String name, Model model){

		if(name != null && name.length() != 0) message = name;
		model.addAttribute("message", message);
		model.addAttribute("list", stringList);

		return "page";
	}

	@PostMapping("/")
	public String addItem(@RequestParam String newitem, Model model){

		System.out.println("New item: "+newitem);
		if(!newitem.equals("")){
			stringList.add(newitem);
		}
		//message = newitem;
		model.addAttribute("message", message);
		model.addAttribute("list", stringList);

		return "page";
	}

	// @GetMapping("/strings")
	// public String displayPageWithStrings(Model model){

	// 	model.addAttribute("list", stringList);
	// 	return "page";
	// }



	public static void main(String[] args) {
		SpringApplication.run(ThymeleafUiApplication.class, args);
	}

}
