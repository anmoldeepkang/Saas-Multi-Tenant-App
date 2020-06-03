package com.demo.repository;


import java.util.List;

import com.demo.model.Message;

public interface MessageRepository {
	
	Message saveMessage(Message message);

	List<Message> findAll();
	
	Message getMessageById(String id);
	
	Message updateMessage(Message message);
	
	void deleteMessage(String id);
	
}
