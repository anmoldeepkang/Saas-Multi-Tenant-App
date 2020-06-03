package com.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Message;
import com.demo.repository.MessageRepository;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageRepository repository;

    public MessageController(MessageRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Message> getMessages(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage(@RequestBody Message message){
        return repository.saveMessage(message);
    }

	
	 @DeleteMapping("/{uuid}")
	 @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteMessage(@PathVariable String uuid) { 
		 repository.deleteMessage(uuid); 
	 }
	 

}
