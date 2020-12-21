package com.creative.task.repository;

import com.creative.task.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(" select count(n) from Notification n where n.delivered = :delivered")
    Long findByDelivered(@Param("delivered") boolean delivered);
}