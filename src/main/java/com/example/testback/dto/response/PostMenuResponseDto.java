package com.example.testback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class PostMenuResponseDto {
    private Long id;
    private final String name;
    private final double price;
    private final String description;
    private Long restaurantId;

    public static class Builder {
        private Long id;
        private Long restaurantId;

        private final String name;
        private final double price;
        private final String description;

        public Builder(String name, double price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public PostMenuResponseDto build() {
        return new PostMenuResponseDto(id, name, price, description, restaurantId);
        }
    }
}

