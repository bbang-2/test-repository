package com.example.testback.controller;

import com.example.testback.common.ApiMappingPattern;
import com.example.testback.dto.request.MenuUpdateRequestDto;
import com.example.testback.dto.request.PostMenuRequestDto;
import com.example.testback.dto.response.MenuResponseDto;
import com.example.testback.dto.response.PostMenuResponseDto;
import com.example.testback.dto.response.ResponseDto;
import com.example.testback.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    private static final String MENU_CREATE = "/menu-create";

    // 새로운 Menu를 특정 Restaurant에 추가하는 API
    @PostMapping(MENU_CREATE)
    public ResponseEntity<ResponseDto<PostMenuResponseDto>> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody PostMenuRequestDto dto
            ){
        ResponseDto<PostMenuResponseDto> response = menuService.createMenu(restaurantId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 특정 Menu 정보를 ID를 통해 조회
    @GetMapping("/{restaurantId}/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> readMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId){
        ResponseDto<MenuResponseDto> response = menuService.readMenu(restaurantId, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Menu 정보 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuUpdateRequestDto dto
            ){
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(restaurantId, menuId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Menu 개별 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId
    ){
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }
}
