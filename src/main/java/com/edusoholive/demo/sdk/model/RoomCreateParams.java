package com.edusoholive.demo.sdk.model;

import lombok.Data;

@Data
public class RoomCreateParams {
    private String name;

    private Long startAt;

    private Long endAt;
}
