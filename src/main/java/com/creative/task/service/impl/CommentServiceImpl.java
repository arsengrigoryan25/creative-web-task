package com.creative.task.service.impl;

import com.creative.task.controller.BusinessLogic;
import com.creative.task.domain.dto.*;
import com.creative.task.entity.Comment;
import com.creative.task.service.NotificationService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.creative.task.repository.CommentRepository;
import com.creative.task.service.CommentService;
import com.creative.task.service.mapper.CommentMapper;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CommentServiceImpl implements CommentService {

    private final NotificationService notificationService;
    private final CommentRepository repository;
    private final CommentMapper mapper;

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @Autowired
    public CommentServiceImpl(NotificationService notificationService, CommentRepository repository) {
        this.notificationService = notificationService;
        this.repository = repository;
        this.mapper = Mappers.getMapper(CommentMapper.class);
    }

    @Override
    public CommentDto creatComment(CommentDto dto) {
        dto.setTime(new Date());
        Comment entity = mapper.dtoToEntity(dto);
        CommentDto result;
        try {
            entity = repository.save(entity);
            BusinessLogic.doSomeWorkOnCommentCreation();
        } catch (RuntimeException e) {
            repository.delete(entity);
            dto.setId(entity.getId());
            throw e;
        }

        result = mapper.entityToDto(entity);
        executorService.submit(() -> createNotification(result));
        return result;
    }

    @Override
    public Result<CommentDto> getCommentsByPage(int page) {
        int pageSize = 10;

        Page<Comment> all = repository.findAll(PageRequest.of(page, pageSize));
        Iterable<CommentDto> commentDtos = mapper.entityListToDtoList(all);

        Result<CommentDto> commentsResult = new Result<>();
        commentsResult.setResultCurrentPage(commentDtos);
        commentsResult.setTotalPage(all.getTotalPages());
        commentsResult.setCurrentPage(page);
        return commentsResult;
    }


    public void createNotification(CommentDto dto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setCommentId(dto.getId());
        notificationDto.setTime(new Date());
        notificationDto.setDelivered(true);
        notificationDto = notificationService.creatNotification(notificationDto);

        try {
            BusinessLogic.doSomeWorkOnNotification();
        } catch (RuntimeException e) {
            notificationDto.setDelivered(false);
            notificationService.updateNotification(notificationDto);
        }
    }
}

