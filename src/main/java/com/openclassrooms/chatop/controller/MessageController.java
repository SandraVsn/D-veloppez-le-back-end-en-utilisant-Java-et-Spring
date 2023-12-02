package com.openclassrooms.chatop.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.ApiDefaultResponse;
import com.openclassrooms.chatop.dto.CreateMessageDto;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "${apiPrefix}/messages")
@Tag(name = "Messages", description = "API for Messages CRUD operations")
public class MessageController {
	
	private MessageService messageService;
	private ModelMapper modelMapper;
	
	@Autowired
	public MessageController(MessageService messageService, ModelMapper modelMapper) {
		this.messageService = messageService;
		this.modelMapper = modelMapper;
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	/* Endpoint to create a new Message
	 * @param createMessageDto : The DTO containing the details of the message to be created.
	 * @return : An ApiDefaultResponse indicating the success or failure of the message creation.
	 */
	@Operation(summary = "Creates a Message")
	@PostMapping("")
	public ApiDefaultResponse createMessage(@RequestBody CreateMessageDto createMessageDto) {
		Message message = messageService.saveMessage(modelMapper.map(createMessageDto, Message.class));
		if(message != null) {
			return new ApiDefaultResponse("Message send with success");
		} else {
			return null;
		}
	}

}
