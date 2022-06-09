package com.edusoholive.demo.dto;

import lombok.Data;

@Data
public class LessonGetRoomTokenParams {
    /**
     * 课时ID
     */
    private Long lessonId;

    /**
     * 用户ID
     */
    private Long userId;
}
