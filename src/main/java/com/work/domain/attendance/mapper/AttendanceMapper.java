package com.work.domain.attendance.mapper;

import com.work.domain.attendance.dto.AttendanceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    AttendanceDto findToday(@Param("userId") Long userId);

    void insertCheckIn(@Param("userId") Long userId);

    void updateCheckOut(@Param("id") Long id);

    List<AttendanceDto> findMyAll(@Param("userId") Long userId);
}