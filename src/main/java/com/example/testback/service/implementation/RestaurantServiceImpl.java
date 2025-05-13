package com.example.testback.service.implementation;

import com.example.testback.common.ResponseMessage;
import com.example.testback.dto.request.RestaurantUpdateRequestDto;
import com.example.testback.dto.response.MenuResponseDto;
import com.example.testback.dto.response.ResponseDto;
import com.example.testback.dto.response.RestaurantDetailResponseDto;
import com.example.testback.dto.response.RestaurantResponseDto;
import com.example.testback.entity.Restaurant;
import com.example.testback.repository.RestaurantRepository;
import com.example.testback.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public ResponseDto<RestaurantResponseDto> createRestaurant(RestaurantResponseDto dto) {
        RestaurantResponseDto responseDto = null;
        Restaurant newRestaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant saved = restaurantRepository.save(newRestaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<RestaurantResponseDto> getRestaurantById(Long id) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.SUCCESS + id));

        responseDto = RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<List<RestaurantResponseDto>> getAllRestaurant() {
        List<RestaurantResponseDto> responseDtos = null;

        List<Restaurant> restaurants = restaurantRepository.findAll();

        responseDtos = restaurants.stream()
                .map(restaurant -> RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    public ResponseDto<RestaurantDetailResponseDto> getRestaurantAllMenus(Long id) {
        RestaurantDetailResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.SUCCESS + id));

        List<MenuResponseDto> menus = restaurant.getMenus().stream()
                .map(menu -> MenuResponseDto.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());

        responseDto = RestaurantDetailResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .menus(menus)
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<RestaurantResponseDto> updateRestaurant(Long id, RestaurantUpdateRequestDto dto) {
        RestaurantResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.SUCCESS + id));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        responseDto = RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.SUCCESS + id));

        restaurant.getMenus().forEach(restaurant::removeMenu);

        restaurantRepository.deleteById(id);
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
