package com.example.ThymeleafUI;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class ThymeleafController {

    @Value("${welcome.recipient}")
	private String message;

	//private List<String> stringList =nt Arrays.asList("one","two","three");
	private ArrayList<String> todoList;

	@GetMapping("/")
	public String displayPage(Model model){

        Todo[] todos = WebClient
            .create("http://localhost:8080/todos/")
            .get()
            .retrieve()
            .bodyToMono(Todo[].class)
            .block();

        todoList = new ArrayList<String>();

        for(int i = 0; i < todos.length; i++)
            todoList.add(todos[i].getName());

		model.addAttribute("message", message);
		model.addAttribute("list", todoList);

		return "page";
	}

	//@GetMapping("/{name}")
	public String displayPage(@PathVariable String name, Model model){

		if(name != null && name.length() != 0) message = name;

		model.addAttribute("message", message);
		model.addAttribute("list", todoList);

		return "page";
	}

	@PostMapping("/")
	public String addItem(@RequestParam String newitem, Model model){

		System.out.println("New item: "+newitem);

        WebClient
            .create("http://localhost:8080/todos/"+newitem)
            .post()
            .retrieve()
            .bodyToMono(Todo.class)
            .block();

        return "redirect:/";
	}

    @PostMapping("/delete")
	public String deleteItem(@RequestParam String olditem, Model model){

		System.out.println("Ol to delete  item: "+olditem);
        WebClient
            .create("http://localhost:8080/todos/"+olditem)
            .delete()
            .retrieve()
            .bodyToMono(Void.class)
            .block();

            // WebClient
            // .create("http://localhost:8080/todos/"+oldItem)
            // .delete()
            // .retrieve()
            // .bodyToMono(Void.class)
            // .block();

        return "redirect:/";
	}

	// @GetMapping("/strings")
	// public String displayPageWithStrings(Model model){

	// 	model.addAttribute("list", stringList);
	// 	return "page";
	// }
    
}
