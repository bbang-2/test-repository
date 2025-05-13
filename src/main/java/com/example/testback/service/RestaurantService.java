package com.example.testback.service;

import com.example.testback.dto.request.RestaurantUpdateRequestDto;
import com.example.testback.dto.response.ResponseDto;
import com.example.testback.dto.response.RestaurantDetailResponseDto;
import com.example.testback.dto.response.RestaurantResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    ResponseDto<RestaurantResponseDto> createRestaurant(@Valid RestaurantResponseDto dto);

    ResponseDto<RestaurantResponseDto> getRestaurantById(Long id);

    ResponseDto<List<RestaurantResponseDto>> getAllRestaurant();

    ResponseDto<RestaurantDetailResponseDto> getRestaurantAllMenus(Long id);

    ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, @Valid RestaurantUpdateRequestDto dto);

    ResponseDto<Void> deleteRestaurant(Long id);
}
