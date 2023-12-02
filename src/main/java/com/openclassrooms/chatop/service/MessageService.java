package com.openclassrooms.chatop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
	
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public Message saveMessage(Message message) {
		message.setCreatedAt(LocalDateTime.now());
		return messageRepository.save(message);
	}

}
