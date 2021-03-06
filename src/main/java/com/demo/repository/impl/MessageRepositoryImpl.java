package com.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Message;
import com.demo.repository.MessageRepository;

@Transactional
@Repository("MessageRepository")
public class MessageRepositoryImpl implements MessageRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public Message saveMessage(Message message) {
		entityManager.persist(message);
		return message;
	}

	@Override
	public List<Message> findAll() {
		return entityManager.createQuery("select m from Message m",Message.class).getResultList();
	}

	@Override
	public Message getMessageById(String id) {
		return entityManager.find(Message.class,id);
	}

	@Override
	public Message updateMessage(Message message) {
		return entityManager.merge(message);
	}

	@Override
	public void deleteMessage(String id) {
		Message message=getMessageById(id);
		if(message!=null)
		entityManager.remove(message);
		
	}
	

}
