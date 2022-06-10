package com.edusoholive.demo.sdk.model;

import com.edusoholive.demo.sdk.common.QueryParams;

import java.util.Map;

public class MemberListParams implements QueryParams {

    private Long roomId;

    private Long offset;

    private Long limit;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @Override
    public Map<String, String> toQueryParams() {
        return Map.of(
                "roomId", roomId.toString(),
                "offset", offset.toString(),
                "limit", limit.toString()
        );
    }
}
