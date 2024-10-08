package com.BigSolutions.FingerPrintApp.Restaurants_microservice.mapper;


import com.BigSolutions.FingerPrintApp.Restaurants_microservice.dto.*;
import com.BigSolutions.FingerPrintApp.Restaurants_microservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MapperService {

    Restaurant toRestaurantEntity(RestaurantRequestDTO dto);
    RestaurantResponseDTO toRestaurantResponseDTO(Restaurant entity);

    Menu toMenuEntity(MenuRequestDTO dto);
    MenuResponseDTO toMenuResponseDTO(Menu entity);

    MenuItem toMenuItemEntity(MenuItemDTO dto);
    MenuItemDTO toMenuItemDTO(MenuItem entity);

    Table toTableEntity(TableRequestDTO dto);
    TableResponseDTO toTableResponseDTO(Table entity);

    Image toImageEntity(ImageRequestDTO dto);
    ImageResponseDTO toImageResponseDTO(Image entity);

    VirtualReality toVirtualRealityEntity(VirtualRealityRequestDTO dto);
    VirtualRealityResponseDTO toVirtualRealityResponseDTO(VirtualReality entity);

    AddressDTO toAddressDTO(Address entity);
    Address toAddressEntity(AddressDTO dto);

    TimeRangeDTO toTimeRangeDTO(TimeRange entity);
    TimeRange toTimeRangeEntity(TimeRangeDTO dto);
/*
    @Mapping(target = "type", expression = "java(ImageType.valueOf(dto.getType()))")
    Image toImageEntity(ImageRequestDTO dto);

    @Mapping(target = "type", expression = "java(entity.getType().name())")
    ImageResponseDTO toImageResponseDTO(Image entity);

    @Mapping(target = "format", expression = "java(VRFormat.valueOf(dto.getFormat()))")
    VirtualReality toVirtualRealityEntity(VirtualRealityRequestDTO dto);

    @Mapping(target = "format", expression = "java(entity.getFormat().name())")
    VirtualRealityResponseDTO toVirtualRealityResponseDTO(VirtualReality entity);

 */
}