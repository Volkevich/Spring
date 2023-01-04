package com.volkevich.InstaZoo.facade;

import com.volkevich.InstaZoo.dto.CommentDTO;
import com.volkevich.InstaZoo.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {
    public CommentDTO commentToCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        comment.setId(comment.getId());
        comment.setUsername(comment.getUsername());
        comment.setMessage(comment.getMessage());
        return commentDTO;
    }
}
