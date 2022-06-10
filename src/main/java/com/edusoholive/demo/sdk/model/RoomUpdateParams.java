package com.edusoholive.demo.sdk.model;

import lombok.Data;

@Data
public class RoomUpdateParams {
    private Long id;

    private String name;

    private Long startAt;

    private Long endAt;
}
