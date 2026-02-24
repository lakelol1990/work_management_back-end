package com.work.domain.notice.mapper;


import com.work.domain.notice.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDto> findAll();

    NoticeDto findById(Long id);

    void insert(NoticeDto notice);

    void delete(Long id);
}