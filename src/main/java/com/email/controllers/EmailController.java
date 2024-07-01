package com.email.controllers;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Email API";
    }

    // api to send email
    @PostMapping(value = "/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        System.out.println(emailRequest);
        boolean result=this.emailService.sendEmail(emailRequest.getTo(), emailRequest.getMessage(), emailRequest.getSubject());
        if(result){
            return ResponseEntity.ok(new EmailResponse("Email sent successfully...!"));
    }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email sending failed...!"));
        }
    }
}
