package com.edusoholive.demo.service;

import com.edusoholive.demo.dto.LessonCreateParams;
import com.edusoholive.demo.dto.LessonDto;
import com.edusoholive.demo.dto.LessonRoomTokenDto;
import com.edusoholive.demo.dto.LessonGetRoomTokenParams;

import java.io.IOException;
import java.util.List;

public interface LessonService {

    /**
     * 列出所有直播课时 (演示程序，不考虑分页)
     */
    List<LessonDto> findAll();

    /**
     * 创建课时
     */
    LessonDto create(LessonCreateParams params);

    /**
     * 获取进入直播课堂的 RoomId 和 Token
     */
    LessonRoomTokenDto getRoomToken(LessonGetRoomTokenParams params);
}
