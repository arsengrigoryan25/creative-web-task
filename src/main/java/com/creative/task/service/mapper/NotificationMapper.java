package com.creative.task.service.mapper;

import com.creative.task.domain.dto.NotificationDto;
import com.creative.task.entity.Notification;
import org.mapstruct.Mapper;

@Mapper
public interface NotificationMapper {

    Notification dtoToEntity(NotificationDto dto);

    NotificationDto entityToDto(Notification entity);

    Iterable<NotificationDto> entityListToDtoList(Iterable<Notification> entity);
}



