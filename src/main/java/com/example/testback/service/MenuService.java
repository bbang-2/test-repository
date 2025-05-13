package com.example.testback.service;

import com.example.testback.dto.request.MenuUpdateRequestDto;
import com.example.testback.dto.request.PostMenuRequestDto;
import com.example.testback.dto.response.MenuResponseDto;
import com.example.testback.dto.response.PostMenuResponseDto;
import com.example.testback.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, @Valid PostMenuRequestDto dto);

    ResponseDto<MenuResponseDto> readMenu(Long restaurantId, Long menuId);


    ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, @Valid MenuUpdateRequestDto dto);

    ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId);

}
