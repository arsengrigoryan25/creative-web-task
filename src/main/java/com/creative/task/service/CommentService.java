package com.creative.task.service;

import com.creative.task.domain.dto.CommentDto;
import com.creative.task.domain.dto.Result;

public interface CommentService {

    /**
     * @param dto
     * @return
     */
    CommentDto creatComment(CommentDto dto) throws InterruptedException;

    /**
     * @param page
     * @return
     */
    Result<CommentDto> getCommentsByPage(int page);
}