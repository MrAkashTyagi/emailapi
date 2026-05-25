package com.email.controller;

import com.email.EmailService;
import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "hello this is my email api";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity<EmailResponse> sendPassword(@RequestBody EmailRequest request){

        boolean result = this.emailService.sendEmail(request.getMessage(), request.getSubject(), request.getTo());
        if (result){
            return ResponseEntity.ok(new EmailResponse("Email is send successfully ..."));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent !!"));
        }

    }



}
