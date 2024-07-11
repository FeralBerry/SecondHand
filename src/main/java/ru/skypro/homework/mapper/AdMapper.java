package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.Ad;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Ad toAdEntity(CreateOrUpdateAd ad);

    @Mapping(target = "image", expression = "java(ad.getImage() != null ? \"/image/\" + ad.getImage().getImagePath() : null)")
    @Mapping(target = "pk", source = "ad.id")
    @Mapping(target = "authorFirstName", source = "ad.user.firstName")
    @Mapping(target = "authorLastName", source = "ad.user.lastName")
    @Mapping(target = "email", source = "ad.user.username")
    @Mapping(target = "phone", source = "ad.user.phone")
    ExtendedAd toExtendedAd(Ad ad);

    @Mapping(target = "image", expression = "java(ad.getImage() != null ? \"/image/\" + ad.getImage().getImagePath() : null)")
    @Mapping(target = "author", source = "ad.user.id")
    @Mapping(target = "pk", source = "ad.id")
    AdDTO toAdDTO(Ad ad);

    default Ads toAds(List<Ad> ads) {
        List<AdDTO> adDTOs = ads.stream()
                .map(this::toAdDTO)
                .collect(Collectors.toList());
        Ads adsDTO = new Ads();
        adsDTO.setResults(adDTOs);
        adsDTO.setCount((long) adDTOs.size());
        return adsDTO;
    }
}