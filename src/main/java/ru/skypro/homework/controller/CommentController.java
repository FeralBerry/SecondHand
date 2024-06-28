package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable("id") Long adId) {
        Comments comments = commentService.getComments(adId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable("id") Long adId,
                                        @RequestBody CreateOrUpdateComment commentRequest) {
        CommentDTO comment = commentService.createComment(commentRequest, adId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or @commentServiceImpl.isOwner(authentication.name, #commentId)")
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Long adId,
                                           @PathVariable("commentId") Long commentId) {
        commentService.delete(adId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or @commentServiceImpl.isOwner(authentication.name, #commentId)")
    public ResponseEntity<?> updateComment(@PathVariable("adId") Long adId, @PathVariable("commentId") Long commentId,
                                           @RequestBody CreateOrUpdateComment commentRequest) {
        CommentDTO updateCommentDTO = commentService.updateComment(adId, commentId, commentRequest);
        return ResponseEntity.ok(updateCommentDTO);
    }
}
