package com.edusoholive.demo.sdk.model;

import lombok.Data;

@Data
public class Room {
    private Long id;

    private String name;

    private Long startAt;

    private Long endAt;

    private Long actualStartAt;

    private Long actualEndAt;

    private String status;

    private Boolean isDeleted;

    private Boolean isReplayGenerated;

    private Long userTotalNum;

    private Long userEnterTotalNum;

    private Long maxOnlineNum;

    private Long chatTotalNum;

    private Long userAvgOnlineDuration;

    private Long createdAt;

    private Long updatedAt;
}
