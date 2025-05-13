package com.example.testback.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMenuRequestDto {
    private String name;
    private double price;
    private String description;
    private Long restaurantId;
}
