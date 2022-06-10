package com.edusoholive.demo.sdk.model;

import lombok.Data;

@Data
public class RoomSimple {
    private Long id;

    private String name;

    private Long startAt;

    private Long endAt;

    private Long createdAt;
}
