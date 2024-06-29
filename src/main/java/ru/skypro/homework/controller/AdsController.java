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

    @GetMapping
    public ResponseEntity<?> getAll() {
        Ads ads = adService.getAll();
        return ResponseEntity.ok(ads);
    }
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createAd(@RequestPart(value = "properties") CreateOrUpdateAd properties,
                                      @RequestPart("image") MultipartFile image) {
        AdDTO createdAd = adService.createAd(properties, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdInfo(@PathVariable("id") Long id) {
        ExtendedAd ad = adService.getAdInfo(id);
        return ResponseEntity.ok(ad);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> updateAd(@PathVariable Long id,
                                      @RequestBody CreateOrUpdateAd ad) {
        AdDTO updatedAd = adService.updateAd(id, ad);
        return ResponseEntity.ok(updatedAd);
    }
    @GetMapping("/me")
    public ResponseEntity<?> getUsersAds() {
        Ads ads = adService.getUsersAds();
        return ResponseEntity.ok(ads);
    }
    @PatchMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(authentication.name, #id)")
    public ResponseEntity<?> updateAdImage(@PathVariable("id") Long id,
                                           @RequestParam("image") MultipartFile image) {
        adService.updateAdImage(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}