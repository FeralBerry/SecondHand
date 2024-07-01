package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO { 

    private Long author;
    private String image;
    private Long pk;
    private Integer price;
    private String title;

}
