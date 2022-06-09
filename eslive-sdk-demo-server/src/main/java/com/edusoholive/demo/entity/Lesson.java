package com.edusoholive.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Lesson {
    /**
     * 课时ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
