package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper adMapper;

    public AdServiceImpl(AdRepository adRepository, UserService userService, ImageService imageService, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.adMapper = adMapper;
    }

    /**
     * Метод выполняется только для чтения, что оптимизирует производительность.
     * Из базы данных вытаскивается список всех объявлений и преобразуется в объект DTO Ads
     * @return объект DTO Ads
     * @see AdMapper
     */
    @Override
    @Transactional(readOnly = true)
    public Ads getAll() {
        var adsList = adRepository.findAll();
        return adMapper.toAds(adsList);
    }
    @Override
    @Transactional(readOnly = true)
    public ExtendedAd getAdInfo(Long id) {
        Ad ad = getAdById(id);
        return adMapper.toExtendedAd(ad);
    }

    @Override
    @Transactional(readOnly = true)
    public Ads getUsersAds() {
        User user = userService.findUser();
        List<Ad> ads = adRepository.findAdsByUserId(user.getId());
        return adMapper.toAds(ads);
    }

    @Override
    public AdDTO createAd(CreateOrUpdateAd adDTO, MultipartFile image) {
        User user = userService.findUser();
        Ad ad = adMapper.toAdEntity(adDTO);
        ad.setUser(user);
        if (image != null && !image.isEmpty()) {
            Image newImage = imageService.saveImage(image);
            ad.setImage(newImage);
            log.info("Image {} saved", newImage.getId());
        }
        adRepository.save(ad);
        return adMapper.toAdDTO(ad);
    }


    @Override
    public AdDTO updateAd(Long id, CreateOrUpdateAd adDTO) {
        Ad ad = getAdById(id);
        ad.setTitle(adDTO.getTitle());
        ad.setPrice(adDTO.getPrice());
        ad.setDescription(adDTO.getDescription());
        adRepository.save(ad);
        return adMapper.toAdDTO(ad);
    }

    @Override
    public void updateAdImage(Long id, MultipartFile image) {
        Ad ad = getAdById(id);
        if (ad.getImage() != null) {
            imageService.deleteImage(ad.getImage().getId());
        }
        Image newImage = imageService.saveImage(image);
        ad.setImage(newImage);
        adRepository.save(ad);
    }
    @Override
    public void deleteAd(Long id) {
        Ad ad = getAdById(id);
        log.info("Ad {} {} deleted", ad.getId(), ad.getTitle());
        adRepository.delete(ad);
    }
    @Override
    public Ad getAdById(Long adId) {
        return adRepository.findById(adId).orElseThrow(() -> {
            log.warn("Ad not found for id: " +  adId);
            return new AdNotFoundException("Ad not found for id: " + adId);
        });
    }
    @Override
    public boolean isOwner(String username, Long id) {
        Ad ad = getAdById(id);
        if (!ad.getUser().getUsername().equals(username)) {
            log.warn("Trying to access foreign ad {} by user {}", id, username);
            return false;
        }
        return true;
    }
}