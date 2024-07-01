package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.Ad;


public interface AdService {
    Ads getAll();
    ExtendedAd getAdInfo(Long id);
    Ads getUsersAds();
    AdDTO createAd(CreateOrUpdateAd ad, MultipartFile image);
    AdDTO updateAd(Long id, CreateOrUpdateAd ad);
    void updateAdImage(Long id, MultipartFile image);
    void deleteAd(Long id);
    Ad getAdById(Long adId);
    boolean isOwner(String username, Long id);
}
