package com.example.testback.controller;

import com.example.testback.common.ApiMappingPattern;
import com.example.testback.dto.request.RestaurantUpdateRequestDto;
import com.example.testback.dto.response.ResponseDto;
import com.example.testback.dto.response.RestaurantDetailResponseDto;
import com.example.testback.dto.response.RestaurantResponseDto;
import com.example.testback.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    private static final String RESTAURANT_CREATE = "/res-create";

    // 새로운 Restaurant을 추가하는 API
    @PostMapping(RESTAURANT_CREATE)
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> createRestaurant(
            @Valid
            @RequestBody RestaurantResponseDto dto
    ) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

    // Restaurant ID를 통해 단일 레스토랑 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // Restaurant 목록 전체 조회 (페이징 기능 추가)
    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurant() {
        ResponseDto<List<RestaurantResponseDto>> restaurant = restaurantService.getAllRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // 특정 Restaurant에 속한 모든 메뉴 조회
    @GetMapping("/{id}/menus")
    public ResponseEntity<ResponseDto<RestaurantDetailResponseDto>> getRestaurantAllMenus(@PathVariable Long id) {
        ResponseDto<RestaurantDetailResponseDto> restaurant = restaurantService.getRestaurantAllMenus(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // Restaurant 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantResponseDto>> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantUpdateRequestDto dto
    ) {
        ResponseDto<RestaurantResponseDto> restaurant = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    // Restaurant 삭제 (하위 메뉴들도 함께 삭제 처리)
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> restaurant = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
