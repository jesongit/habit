package com.example.habit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(name = "NoteDto", description = "笔记基本信息")
public class NoteDto {

    @Schema(description = "图片列表")
    private List<MultipartFile> files;

    @Schema(description = "内容")
    private String content;
}
