package ru.skypro.homework.dto.comment;

import lombok.Data;

@Data
public class CommentDTO {

    private Long author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Long pk;
    private String text;
}
