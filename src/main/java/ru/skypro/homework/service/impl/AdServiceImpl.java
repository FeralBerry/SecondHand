package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;

    @Override
    @Transactional(readOnly = true)
    public Ads getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAdInfo(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getUsersAds() {
        return null;
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAd adDTO, MultipartFile image) {
        return null;
    }


    @Override
    public AdDTO updateAd(Long id, CreateOrUpdateAd adDTO) {
        return null;
    }

    @Override
    public void updateAdImage(Long id, MultipartFile image) {

    }
    @Override
    public void deleteAd(Long id) {

    }
    @Override
    @Transactional(readOnly = true)
    public Ad getAdById(Long adId) {
        return null;
    }
    @Override
    public boolean isOwner(String username, Long id) {
        return false;
    }
}