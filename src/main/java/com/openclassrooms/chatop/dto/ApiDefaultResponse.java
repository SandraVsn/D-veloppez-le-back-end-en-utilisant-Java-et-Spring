package com.openclassrooms.chatop.dto;

import lombok.Data;

@Data
public class ApiDefaultResponse {
	
    private String message;

    public ApiDefaultResponse(String message) {
        this.message = message;
    }

}
