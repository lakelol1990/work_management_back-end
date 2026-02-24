package com.work.domain.attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceDto {

    private Long id;
    private Long userId;

    // DATE → LocalDate
    private LocalDate workDate;

    // TIMESTAMP → LocalDateTime
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}