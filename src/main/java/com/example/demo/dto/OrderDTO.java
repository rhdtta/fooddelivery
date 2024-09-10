package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long userId;
    private Long restaurantId;
    private List<Long> dishIds;
}
