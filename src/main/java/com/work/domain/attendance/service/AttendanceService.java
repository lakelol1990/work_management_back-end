package com.work.domain.attendance.service;

import com.work.domain.attendance.dto.AttendanceDto;
import com.work.domain.attendance.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceMapper attendanceMapper;

    /** 오늘 기록 조회 */
    public AttendanceDto getToday(Long userId){
        return attendanceMapper.findToday(userId);
    }

    /** 출근 */
    public void checkIn(Long userId){
        AttendanceDto today = attendanceMapper.findToday(userId);

        if(today != null){
            throw new RuntimeException("이미 출근했습니다.");
        }

        attendanceMapper.insertCheckIn(userId);
    }

    /** 퇴근 */
    public void checkOut(Long userId){
        AttendanceDto today = attendanceMapper.findToday(userId);

        if(today == null){
            throw new RuntimeException("출근 기록이 없습니다.");
        }

        if(today.getCheckOut() != null){
            throw new RuntimeException("이미 퇴근했습니다.");
        }

        attendanceMapper.updateCheckOut(today.getId());
    }

    /** 내 전체 기록 */
    public List<AttendanceDto> getMyAll(Long userId){
        return attendanceMapper.findMyAll(userId);
    }


}