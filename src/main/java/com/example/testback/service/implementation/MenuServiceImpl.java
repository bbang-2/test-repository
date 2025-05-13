package com.example.testback.service.implementation;

import com.example.testback.common.ResponseMessage;
import com.example.testback.dto.request.MenuUpdateRequestDto;
import com.example.testback.dto.request.PostMenuRequestDto;
import com.example.testback.dto.response.MenuResponseDto;
import com.example.testback.dto.response.PostMenuResponseDto;
import com.example.testback.dto.response.ResponseDto;
import com.example.testback.entity.Menu;
import com.example.testback.entity.Restaurant;
import com.example.testback.repository.MenuRepository;
import com.example.testback.repository.RestaurantRepository;
import com.example.testback.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Override
    public ResponseDto<PostMenuResponseDto> createMenu(Long restaurantId, PostMenuRequestDto dto) {
        PostMenuResponseDto responseDto = null;

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu newMenu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);

        responseDto = new PostMenuResponseDto.Builder(savedMenu.getName(), savedMenu.getPrice(), savedMenu.getDescription())
                .id(savedMenu.getId())
                .restaurantId(savedMenu.getRestaurant().getId())
                .build();
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<MenuResponseDto> readMenu(Long restaurantId, Long menuId) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        responseDto = MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, MenuUpdateRequestDto dto) {
        MenuResponseDto responseDto = null;

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        if(!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.setName(dto.getName());
        menu.setPrice(dto.getPrice());
        menu.setDescription(dto.getDescription());

        Menu updatedMenu = menuRepository.save(menu);

        responseDto = MenuResponseDto.builder()
                .id(updatedMenu.getId())
                .name(updatedMenu.getName())
                .price(updatedMenu.getPrice())
                .description(updatedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu does not belong to the specified Restaurant"));

        if(!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu does not belong to the specified Restaurant");
        }

        menu.getRestaurant().removeMenu(menu);

        menuRepository.delete(menu);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
