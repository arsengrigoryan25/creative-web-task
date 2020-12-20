package com.creative.task.controller;

import com.creative.task.domain.dto.NotificationDto;
import com.creative.task.domain.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.creative.task.service.NotificationService;

@RequestMapping("/notification")
@RestController
public class NotificationController {

    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{page}")
    public ResponseEntity<Result<NotificationDto>> getNotificationsByPage(@PathVariable int page) {
        ResponseEntity<Result<NotificationDto>> responseEntity;
        Result<NotificationDto> notificationDtoResult = new Result<>();
        try{
            notificationDtoResult = service.getNotificationByPage(page);
            responseEntity = new ResponseEntity<>(notificationDtoResult, HttpStatus.OK);
        } catch(Exception e){
            responseEntity = new ResponseEntity<>(notificationDtoResult, HttpStatus.BAD_GATEWAY);
        }

        return responseEntity;
    }
}

