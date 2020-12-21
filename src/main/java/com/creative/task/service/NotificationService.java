package com.creative.task.service;

import com.creative.task.domain.dto.NotificationDto;
import com.creative.task.domain.dto.Result;

public interface NotificationService {


    NotificationDto creatNotification(NotificationDto dto);


    NotificationDto updateNotification(NotificationDto dto);


    Result<NotificationDto> getNotificationByPage(int page);

}