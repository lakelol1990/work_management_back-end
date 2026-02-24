package com.work.domain.attendance.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.work.domain.attendance.dto.AttendanceDto;
import com.work.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    /** 현재 로그인 userId 가져오는거 입니다.*/
    private Long getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getName());
    }

    /** 출근 입니다. */
    @PostMapping("/check-in")
    public void checkIn(){
        attendanceService.checkIn(getUserId());
    }

    /** 퇴근 입니다. */
    @PostMapping("/check-out")
    public void checkOut(){
        attendanceService.checkOut(getUserId());
    }

    /** 오늘 기록 입니다. */
    @GetMapping("/today")
    public AttendanceDto today(){
        return attendanceService.getToday(getUserId());
    }

    /** 내 전체 기록 입니다.  */
    @GetMapping("/my")
    public List<AttendanceDto> my(){
        return attendanceService.getMyAll(getUserId());
    }
}