package com.example.testback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
