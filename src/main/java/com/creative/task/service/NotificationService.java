package com.creative.task.service;

import com.creative.task.domain.dto.NotificationDto;
import com.creative.task.domain.dto.Result;

public interface NotificationService {

    /**
     * @param dto
     * @return
     */
    NotificationDto creatNotification(NotificationDto dto);

    /**
     * @param dto
     * @return
     */
    NotificationDto updateNotification(NotificationDto dto);

    /**
     *
     *
     * @param page
     * @return
     */
    Result<NotificationDto> getNotificationByPage(int page);

    /**
     * @return
     */
    Long getCountDeliveredNotifications();
}