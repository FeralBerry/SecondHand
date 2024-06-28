package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Long adId) {
        return null;
    }
    public User getCurrentAuthenticatedUser() {
        return null;
    }
    @Override
    public CommentDTO createComment(CreateOrUpdateComment comment, Long adId) {
        return null;
    }
    @Override
    public void delete(Long adId, Long commentId) {

    }
    @Override
    public CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment commentRequest) {
        return null;
    }
    public boolean isOwner(String username, Long id) {
        return false;
    }
}
