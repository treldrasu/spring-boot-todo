package de.schulung.todoTogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.schulung.todoTogether.Model.ToDo;
import de.schulung.todoTogether.Model.ToDoRepository;

@SpringBootApplication
public class TodoTogetherApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TodoTogetherApplication.class, args);

		ToDoRepository tr = ctx.getBean(ToDoRepository.class);
 
	}

}
