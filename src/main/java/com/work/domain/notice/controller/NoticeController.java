package com.work.domain.notice.controller;

import com.work.domain.notice.service.NoticeService;
import com.work.domain.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public List<NoticeDto> list() {
        return noticeService.getNotices();
    }

    @GetMapping("/{id}")
    public NoticeDto detail(@PathVariable Long id) {
        return noticeService.getNotice(id);
    }
}