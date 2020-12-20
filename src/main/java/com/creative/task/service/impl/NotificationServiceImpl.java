package com.creative.task.service.impl;

import com.creative.task.domain.dto.*;
import com.creative.task.entity.Notification;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.creative.task.repository.NotificationRepository;
import com.creative.task.service.NotificationService;
import com.creative.task.service.mapper.NotificationMapper;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
        mapper = Mappers.getMapper(NotificationMapper.class);
    }

    @Override
    public NotificationDto creatNotification(NotificationDto dto) {
        Notification entity = mapper.dtoToEntity(dto);
        entity = repository.save(entity);

        return mapper.entityToDto(entity);
    }

    @Override
    public NotificationDto updateNotification(NotificationDto dto) {
        Notification entity = new Notification();
        Optional<Notification> isPresent = repository.findById(dto.getId());
        if(isPresent.isPresent()){
            entity = isPresent.get();
//            entity.setCommentId(dto.getCommentId());
//            entity.setTime(dto.getTime());
            entity.setDelivered(dto.isDelivered());
            entity = repository.save(entity);
        }
        return mapper.entityToDto(entity);
    }


    @Override
    public Result<NotificationDto> getNotificationByPage(int page) {
        int pageSize = 10;

        Page<Notification> all = repository.findAll(PageRequest.of(page, pageSize));
        Iterable<NotificationDto> notificationDtos = mapper.entityListToDtoList(all);

        Result<NotificationDto> notificationResult = new Result<>();
        notificationResult.setResultCurrentPage(notificationDtos);
        notificationResult.setTotalPage(all.getTotalPages());
        notificationResult.setCurrentPage(page);
        return notificationResult;
    }

    @Override
    public Long getCountDeliveredNotifications() {
        return repository.findByDelivered(true);
    }

}