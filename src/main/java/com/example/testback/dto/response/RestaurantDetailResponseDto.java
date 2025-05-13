package com.example.testback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetailResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<MenuResponseDto> menus;
}
