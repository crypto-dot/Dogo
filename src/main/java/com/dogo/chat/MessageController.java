package com.dogo.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {
	@Autowired
	MessageRepository dao;
	@GetMapping("/chat")
	public List<Message> getMessages() {
		List<Message> foundMessages = dao.findAll();
		return foundMessages;
	}
	@PostMapping("/chat") 
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {
		Message createMessage = dao.save(message);
		return ResponseEntity.ok(createMessage);
	}
	//Takes get requests to find a specific message with the ID
	@GetMapping("/chat/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable(value="id") Integer id) {
		Message findMessage = dao.findById(id).orElse(null);
		if(findMessage == null) {
			return ResponseEntity.notFound().header("Message","Nothing found with that ID").build();
		}
		return ResponseEntity.ok(findMessage);
	}
	@DeleteMapping("/chat/{id}")
	public ResponseEntity<Message> deleteMessage(@PathVariable(value="id") Integer id) {
		Message findMessage = dao.findById(id).orElse(null);
		if(findMessage == null) {
			return ResponseEntity.notFound().header("Message", "Nothing was found with that ID").build();
			
		} else {
			dao.deleteById(id);
		}
		return ResponseEntity.ok().build();
	}
	@PutMapping("/chat")
	public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
		if(message.getId() == null) {
			return ResponseEntity.unprocessableEntity().header("Message", "Must have a valid ID associated with your message object to update an existing one!").build();
		}
		Message findMessage = dao.findById(message.getId()).orElse(null);
		if(findMessage == null) {
			return ResponseEntity.notFound().header("Message", "Nothing was found with that ID").build();
		} else {
			dao.save(message);
		}
		return ResponseEntity.ok().build();
	}
	@GetMapping("/math/add/{x}/{y}")
	public int add(@PathVariable(value="x") int x, @PathVariable(value="y") int y) {
		return x + y;
	}
	
}
