package com.edusoholive.demo.mapper;

import com.edusoholive.demo.dto.LessonCreateParams;
import com.edusoholive.demo.dto.LessonDto;
import com.edusoholive.demo.entity.Lesson;
import com.edusoho.live.sdk.model.RoomCreateParams;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toEntity(LessonCreateParams params) {
        var lesson = new Lesson();
        lesson.setName(params.getName());
        lesson.setStartAt(params.getStartAt());
        lesson.setEndAt(params.getEndAt());
        lesson.setCreatedAt(System.currentTimeMillis());

        return lesson;
    }

    public LessonDto toDto(Lesson lesson) {
        var dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setRoomId(lesson.getRoomId());
        dto.setStartAt(lesson.getStartAt());
        dto.setEndAt(lesson.getEndAt());
        dto.setCreatedAt(lesson.getCreatedAt());
        return dto;
    }

    public RoomCreateParams toRoomCreateParams(LessonCreateParams params) {
        var createParams = new RoomCreateParams();
        createParams.setName(params.getName());
        createParams.setStartAt(params.getStartAt());
        createParams.setEndAt(params.getEndAt());

        return createParams;
    }
}
