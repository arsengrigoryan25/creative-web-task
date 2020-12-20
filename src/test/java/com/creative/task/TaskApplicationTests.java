package com.creative.task;

import com.creative.task.domain.dto.*;
import com.creative.task.service.*;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class TaskApplicationTests {

    @Autowired
    private MockMvc restMockMvc;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotificationService notificationService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private static final List<String> createdListOfCommentsIdes = new CopyOnWriteArrayList<>();
    private static final List<String> notCreatedComments = new CopyOnWriteArrayList<>();
    private static final List<String> notCreatedNotifications = new CopyOnWriteArrayList<>();

    private static final AtomicInteger countAddedCommentsAndNotifications = new AtomicInteger(0);
    private static final AtomicInteger countDeliveredNotifications = new AtomicInteger(0);

    @Test
    void testCreateCommentsAndNotifications() throws InterruptedException {
        int start = 1;
        int end = 100;
        int i = 1;
        do {
            createCommentsAndNotifications(start, end);
            start += 100;
            end += 100;
            i++;
        } while (i < 11);

        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }

    void createCommentsAndNotifications(int start, int end) {

        for (int i = start; i < end; i++) {
            CommentDto comment = new CommentDto();
            comment.setComment("Comment - " + i);
            executorService.submit(() -> {
                ResultActions perform;
                String content;
                String idReturnedDto;
                String commentDeleted;

                try {
                    perform = restMockMvc.perform(post("/comment/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(comment))
                    );
                    content = perform.andReturn().getResponse().getContentAsString();
                    String[] contentArr = content.split(",");
                    idReturnedDto = contentArr[0].split(":")[1];

                    if (contentArr.length == 4) {
                        commentDeleted = contentArr[3].split(":")[1].substring(0,4);
                        if (commentDeleted.equals("true")) {
                            String commentReturnedDto = contentArr[1].split(":")[1];
                            notCreatedComments.add(commentReturnedDto);
                            notCreatedNotifications.add(idReturnedDto);
                        }
                    } else {
                        countAddedCommentsAndNotifications.incrementAndGet();
                        createdListOfCommentsIdes.add(idReturnedDto);
                    }
                } catch (Exception e) {
                    fail();
                }
            });
        }
    }

    @Test
    void testGetCommentByPage() {
        Result<CommentDto> commentsResult = commentService.getCommentsByPage(0);
        int length = commentsResult.getTotalPage();
        List<String> allCommentIdesOfNotifications = getAllCommentIdesOfNotifications();

        for (int i = 1; i <= length; i++) {
            for (CommentDto comment : commentsResult.getResultCurrentPage()) {
                String id = comment.getId() == null ? null : comment.getId().toString();
                if (id != null && createdListOfCommentsIdes.contains(id)) {
                    boolean isCreatedNotification = allCommentIdesOfNotifications.contains(id);
                    if (!isCreatedNotification) {
                        fail("The notification was not created, but it should have been");
                    }
                } else {
                    fail("The comment was not created, but it should have been");
                }
                if (notCreatedComments.contains(comment.getComment())) {
                    fail("The comment was created but should not have been created");
                }
                boolean isCreatedNotification = notCreatedNotifications.contains(id);
                if (isCreatedNotification) {
                    fail("The notification was created but should not have been created");
                }
            }

            commentsResult = commentService.getCommentsByPage(i);
        }
    }

    public List<String> getAllCommentIdesOfNotifications() {
        List<String> allCommentIdesOfNotifications = new ArrayList<>();

        Result<NotificationDto> notificationResult = notificationService.getNotificationByPage(0);
        int length = notificationResult.getTotalPage();
        for (int i = 1; i <= length; i++) {
            for (NotificationDto notification : notificationResult.getResultCurrentPage()) {
                allCommentIdesOfNotifications.add(notification.getCommentId() + "");
                if (notification.isDelivered()) {
                    countDeliveredNotifications.incrementAndGet();
                }
            }
            notificationResult = notificationService.getNotificationByPage(i);
        }
        return allCommentIdesOfNotifications;
    }

    @AfterAll
    public static void testCountPercentAddedCommentsAndNotifications() {
        int countAllComments = 1000;
        float percentAddedComments = (float) countAddedCommentsAndNotifications.intValue() / countAllComments * 100;
        float percentDeliveredNotifications = (float) countDeliveredNotifications.intValue() / countAddedCommentsAndNotifications.intValue() * 100;
        System.out.println("Percent added comments -  " + percentAddedComments + " %");
        System.out.println("Percent delivered notification - " + percentDeliveredNotifications + " %");
    }
}