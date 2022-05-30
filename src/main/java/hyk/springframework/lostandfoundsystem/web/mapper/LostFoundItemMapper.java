package hyk.springframework.lostandfoundsystem.web.mapper;

import hyk.springframework.lostandfoundsystem.domain.LostFoundItem;
import hyk.springframework.lostandfoundsystem.web.dto.LostFoundItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Htoo Yanant Khin
 **/
@Mapper
public interface LostFoundItemMapper {
    LostFoundItemMapper INSTANCE = Mappers.getMapper( LostFoundItemMapper.class );

    LostFoundItemDto lostFoundItemToLostFoundItemDto(LostFoundItem lostFoundItem);

    LostFoundItem lostFoundItemDtoToLostFoundItem(LostFoundItemDto lostFoundItemDto);
}
