package com.edusoholive.demo.sdk.model;

import lombok.Data;

import java.util.Map;

@Data
public class MemberListParams implements QueryParams {

    private Long roomId;

    private Long offset;

    private Long limit;

    @Override
    public Map<String, Object> toQueryParams() {
        return Map.of(
                "roomId", roomId,
                "offset", offset,
                "limit", limit
        );
    }
}