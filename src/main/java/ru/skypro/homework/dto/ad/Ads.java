package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ads {
    private Long count;
    private List<AdDTO> results;
}
