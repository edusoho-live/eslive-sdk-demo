package com.edusoholive.demo.dto;

import lombok.Data;

@Data
public class LessonDto {
    /**
     * 课时ID
     */
    private Long id;

    /**
     * 课时名称
     */
    private String name;

    /**
     * 直播课堂ID
     */
    private Long roomId;

    /**
     * 上课开始时间
     */
    private Long startAt;

    /**
     * 上课结束时间
     */
    private Long endAt;

    /**
     * 课时创建时间
     */
    private Long createdAt;
}
