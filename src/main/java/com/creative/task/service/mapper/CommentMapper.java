package com.creative.task.service.mapper;

import org.mapstruct.Mapper;
import com.creative.task.domain.dto.CommentDto;
import com.creative.task.entity.Comment;

@Mapper
public interface CommentMapper {

    Comment dtoToEntity(CommentDto dto);

    CommentDto entityToDto(Comment entity);

    Iterable<CommentDto> entityListToDtoList(Iterable<Comment> entity);
}



