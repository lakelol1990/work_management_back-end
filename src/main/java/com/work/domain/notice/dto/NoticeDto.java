package com.work.domain.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;



@Data
public class NoticeDto {

    private LocalDateTime createdAt;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
}