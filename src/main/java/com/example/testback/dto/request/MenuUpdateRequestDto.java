package com.example.testback.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuUpdateRequestDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private double price;
    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String description;
}
