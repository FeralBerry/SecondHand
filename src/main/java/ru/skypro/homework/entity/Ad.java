package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Integer price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_images_id", referencedColumnName = "id")
    private AdImage adImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
