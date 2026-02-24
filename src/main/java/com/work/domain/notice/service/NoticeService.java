package com.work.domain.notice.service;

import com.work.domain.notice.mapper.NoticeMapper;
import com.work.domain.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<NoticeDto> getNotices() {
        return noticeMapper.findAll();
    }

    public NoticeDto getNotice(Long id) {
        return noticeMapper.findById(id);
    }
}