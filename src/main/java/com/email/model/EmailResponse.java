package com.email.model;

import lombok.Data;

@Data
public class EmailResponse {
    String token;

    public EmailResponse(String token) {
        this.token = token;
    }
}
