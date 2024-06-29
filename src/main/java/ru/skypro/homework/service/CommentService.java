package ru.skypro.homework.service;


import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Long adId);
    CommentDTO createComment(CreateOrUpdateComment comment, Long adId);
    void delete(Long adId, Long commentId);
    CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment commentRequest);
}

