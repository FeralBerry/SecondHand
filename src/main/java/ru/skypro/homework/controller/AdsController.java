package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.service.AdService;

@RestController
@RequestMapping("ads")
@AllArgsConstructor
@Validated
public class AdsController {

    private final AdService adService;

    /**
     * Получение списка всех объявлений
     * @return возвращает статус 200 и список всех объявлений собранный
     * в формате DTO количество объявлений и список объявлений
     * @author FeralBerry
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        Ads ads = adService.getAll();
        return ResponseEntity.ok(ads);
    }

    /**
     * Пост запрос для добавления объявления
     * MediaType.MULTIPART_FORM_DATA_VALUE необходимый параметр для принятия изображения
     * @param properties получение данных в формате DTO CreateOrUpdateAd
     *                   String title;
     *                   Integer price;
     *                   String description;
     * @param image изображение в формате MultipartFile
     * @return возвращает статус ответа и при успешном отчете новое объявление в формате DTO AdDTO
     * @author FeralBerry
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createAd(@RequestPart(value = "properties") CreateOrUpdateAd properties,
                                      @RequestPart("image") MultipartFile image) {
        AdDTO createdAd = adService.createAd(properties, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
    }

    /**
     * Получение информации по id об объявлении
     * @param id объявления
     * @return возвращает статус ответа и объект в формате DTO ExtendedAd
     *          Long pk;
     *          String authorFirstName;
     *          String authorLastName;
     *          String description;
     *          String email;
     *          String image;
     *          String phone;
     *          Integer price;
     *          String title;
     * @author FeralBerry
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdInfo(@PathVariable("id") Long id) {
        ExtendedAd ad = adService.getAdInfo(id);
        return ResponseEntity.ok(ad);
    }

    /**
     * Удаление объявления
     * Проверяется роль пользователя на админа или если пользователь является владельцем объявления
     * @param id объявления
     * @return возвращает статус ответа
     * @author FeralBerry
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Изменение объявления
     * @param id объявления
     * @param ad объект в формате DTO CreateOrUpdateAd
     *           String title;
     *           Integer price;
     *           String description;
     * @return возвращает статус ответа и объект в формате DTO AdDTO
     *          Long author;
     *          String image;
     *          Long pk;
     *          Integer price;
     *          String title;
     * @author FeralBerry
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> updateAd(@PathVariable Long id,
                                      @RequestBody CreateOrUpdateAd ad) {
        AdDTO updatedAd = adService.updateAd(id, ad);
        return ResponseEntity.ok(updatedAd);
    }

    /**
     * Просмотр объявлений добавленных пользователем
     * @return возвращает статус ответа и список всех объявлений пользователя в формате DTO Ads
     *          Long count;
     *          List<AdDTO> results;
     *              Long author;
     *              String image;
     *              Long pk;
     *              Integer price;
     *              String title;
     * @author FeralBerry
     */
    @GetMapping("/me")
    public ResponseEntity<?> getUsersAds() {
        Ads ads = adService.getUsersAds();
        return ResponseEntity.ok(ads);
    }

    /**
     * Изменение изображения по id
     * Проверяется роль пользователя на админа или если пользователь является владельцем объявления
     * @param id изображения
     * @param image новое изображение для обновления
     * @return возвращает статус ответа
     * @author FeralBerry
     */
    @PatchMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> updateAdImage(@PathVariable("id") Long id,
                                           @RequestParam("image") MultipartFile image) {
        adService.updateAdImage(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}