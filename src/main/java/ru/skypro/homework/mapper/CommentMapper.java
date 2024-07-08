package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {

    default Comments toComments(List<Comment> comments) {
        List<CommentDTO> commentDTOS = comments.stream()
                .map(this::toCommentDTO)
                .collect(Collectors.toList());
        Comments commentsDTO = new Comments();
        commentsDTO.setResults(commentDTOS);
        commentsDTO.setCount((long) commentDTOS.size());
        return commentsDTO;
    }

    @Mapping(target = "author", source = "comment.user.id")
    @Mapping(target = "authorImage", expression = "java(mapUserImageToUrl(comment.getUser()))")
    @Mapping(target = "authorFirstName", source = "comment.user.firstName")
    @Mapping(target = "pk", source = "comment.id")
    CommentDTO toCommentDTO(Comment comment);

    default Long mapLocalDateTimeToLong(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    default String mapUserImageToUrl(User user) {
        if (user != null && user.getAvatar() != null) {
            return "/image/" + user.getAvatar().getId();
        }
        return null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ad", ignore = true)
    Comment toComment(CreateOrUpdateComment comment);
}
