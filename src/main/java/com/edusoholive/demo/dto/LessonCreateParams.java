package com.edusoholive.demo.dto;

import lombok.Data;

@Data
public class LessonCreateParams {

    /**
     * 课时名称
     */
    private String name;

    /**
     * 上课开始时间
     */
    private Long startAt;

    /**
     * 上课结束时间
     */
    private Long endAt;

}
