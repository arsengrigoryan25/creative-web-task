package com.creative.task.service;

import com.creative.task.domain.dto.CommentDto;
import com.creative.task.domain.dto.Result;

public interface CommentService {

    CommentDto creatComment(CommentDto dto) throws InterruptedException;

    Result<CommentDto> getCommentsByPage(int page);
}