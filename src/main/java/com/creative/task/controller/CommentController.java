package com.creative.task.controller;

import com.creative.task.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.creative.task.service.CommentService;

@RestController
@RequestMapping("/comment")
@EnableAutoConfiguration
@ComponentScan
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto){
        ResponseEntity<CommentDto> commentDtoResponseEntity;
        if (dto.getComment() == null){
            return new ResponseEntity("Comment is empty", HttpStatus.BAD_REQUEST);
        }
        try{
            dto = service.creatComment(dto);
            commentDtoResponseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e){
            CommentDto_ dto_ = new CommentDto_(dto,true);
            commentDtoResponseEntity = new ResponseEntity<>(dto_, HttpStatus.BAD_GATEWAY);
        }

        return commentDtoResponseEntity;
    }

    @GetMapping("/{page}")
    public ResponseEntity<Result<CommentDto>> getCommentsByPage(@PathVariable int page) {
        ResponseEntity<Result<CommentDto>> responseEntity;
        Result<CommentDto> commentsByPage = new Result<>();
        try{
            commentsByPage = service.getCommentsByPage(page);
            responseEntity = new ResponseEntity<>(commentsByPage, HttpStatus.OK);
        } catch(Exception e){
            responseEntity = new ResponseEntity<>(commentsByPage, HttpStatus.BAD_GATEWAY);
        }

        return responseEntity;
    }
}



