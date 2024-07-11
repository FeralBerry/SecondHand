package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UnauthorizedAccessException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdService adService;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdService adService, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adService = adService;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Long adId) {
        List<Comment> comments = commentRepository.findByAdId(adId);
        return commentMapper.toComments(comments);
    }
    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return userRepository.findByUsername(username).orElse(null);
        } else {
            throw new UnauthorizedAccessException("Пользователь не аутентифицирован");
        }
    }
    @Override
    public CommentDTO createComment(CreateOrUpdateComment comment, Long adId) {
        Ad ad = adService.getAdById(adId);
        User currentUser = getCurrentAuthenticatedUser();

        Comment newComment = commentMapper.toComment(comment);
        newComment.setAd(ad);
        newComment.setUser(currentUser);

        Comment saveComment = commentRepository.save(newComment);
        return commentMapper.toCommentDTO(saveComment);
    }
    @Override
    public void delete(Long adId, Long commentId) {
        adService.getAdById(adId);
        Comment deleteComment = commentRepository.findByAdIdAndId(adId, commentId)
                .orElseThrow(() -> {
                    log.warn("Comment " + commentId + " not found for update");
                    return new CommentNotFoundException("Comment " + commentId + " not found for ad " +  adId);
                });
        commentRepository.delete(deleteComment);
        log.info("Comments with id: {} successfully deleted for ad with id: {}", commentId, adId);
    }
    @Override
    public CommentDTO updateComment(Long adId, Long commentId, CreateOrUpdateComment commentRequest) {
        Comment updateComment = commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.warn("Comment " + commentId + " not found for update");
                    return new CommentNotFoundException("Comment " + commentId + " not found.");
                });
        updateComment.setText(commentRequest.getText());
        commentRepository.save(updateComment);
        log.info("Comment [{}] for ad [{}] successfully updated", commentId, adId);
        return commentMapper.toCommentDTO(updateComment);
    }
    public boolean isOwner(String username, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Comment " + id + " not found");
                    return new CommentNotFoundException("Comment " + id + " not found");
                });
        if (!comment.getUser().getUsername().equals(username)) {
            log.warn("Trying to access foreign comment {} by user {}", id, username);
            return false;
        }
        return true;
    }
}
