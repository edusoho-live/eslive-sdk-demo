package com.edusoholive.demo.controller;

import com.edusoholive.demo.dto.LessonCreateParams;
import com.edusoholive.demo.dto.LessonDto;
import com.edusoholive.demo.dto.LessonGetRoomTokenParams;
import com.edusoholive.demo.dto.LessonRoomTokenDto;
import com.edusoholive.demo.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/api/lesson/create")
    public LessonDto create(@RequestBody LessonCreateParams params) {
        return lessonService.create(params);
    }

    @GetMapping("/api/lesson/findAll")
    public List<LessonDto> findAll() {
        return lessonService.findAll();
    }

    @GetMapping("/api/lesson/getRoomTokenForSpeaker")
    public LessonRoomTokenDto getRoomTokenForTeacher(@RequestParam Long id) {
        var params = new LessonGetRoomTokenParams();
        params.setLessonId(id);
        // 省略：获取当前登录用户ID
        // 演示程序，假设老师的用户 ID 为 1
        params.setUserId(1L);
        return lessonService.getRoomToken(params);
    }

    @GetMapping("/api/lesson/getRoomTokenForViewer")
    public LessonRoomTokenDto getRoomTokenForStudent(@RequestParam Long id) {
        var params = new LessonGetRoomTokenParams();
        params.setLessonId(id);
        // 省略：获取当前登录用户ID
        // 演示程序，假设学生的用户 ID 为 2
        params.setUserId(2L);
        return lessonService.getRoomToken(params);
    }
}
