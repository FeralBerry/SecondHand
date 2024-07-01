package ru.skypro.homework.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Comments {

    private Long count;
    private List<CommentDTO> results;
}
