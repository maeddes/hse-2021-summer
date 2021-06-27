package com.example.KotlinDemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


@SpringBootApplication
class KotlinDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinDemoApplication>(*args)
}

@RestController
@RequestMapping("todos")
class RestApi(val todoRepository: TodoRepository){

	@GetMapping("/", produces = ["application/json"])
	fun list(): List<Todo>{
		return todoRepository.findAll() as List<Todo>
	}

	@GetMapping(path = ["/{name}"], produces = ["application/json"])
	fun getTodo(@PathVariable name: String?): Todo? {
		val optional: Optional<Todo> = todoRepository.findById(name!!)
		return optional.get()
	}

	@PostMapping(consumes = ["application/json"], produces = ["application/json"])
	fun addTodo(@RequestBody todo: Todo?): Todo? {
		todoRepository.save(todo)
		return todo
	}

	@PostMapping(path = ["/{name}/{priority}"], produces = ["application/json"])
	fun addTodo(@PathVariable name: String?, @PathVariable priority: Int): Todo? {
		val todo = Todo(name, priority)
		todoRepository.save(todo)
		return todo
	}

	@DeleteMapping(path = ["/{name}"], produces = ["application/json"])
	fun deleteTodo(@PathVariable name: String?): Todo? {
		todoRepository.deleteById(name!!)
		return null
	}

}

interface TodoRepository : CrudRepository<Todo,String>

@Entity class Todo {
	@Id
	@Basic(optional = false)
	@Column(name = "name", unique = true, nullable = false)
	var name: String? = null,
	var priority: Int = 2
}