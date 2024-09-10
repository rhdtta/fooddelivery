package com.example.demo.dto;

import com.example.demo.enums.RiderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RiderStatusUpdateDTO {
    private Long riderId;
    private RiderStatus status;

}
