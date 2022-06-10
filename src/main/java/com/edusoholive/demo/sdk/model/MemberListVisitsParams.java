package com.edusoholive.demo.sdk.model;

import com.edusoholive.demo.sdk.common.QueryParams;
import lombok.Data;

import java.util.Map;

@Data
public class MemberListVisitsParams implements QueryParams {

    private Long roomId;

    private Long offset;

    private Long limit;

    @Override
    public Map<String, String> toQueryParams() {
        return Map.of(
                "roomId", roomId.toString(),
                "offset", offset.toString(),
                "limit", limit.toString()
        );
    }
}