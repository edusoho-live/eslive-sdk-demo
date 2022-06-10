package com.edusoholive.demo.sdk.common;

import lombok.Data;

import java.util.List;

@Data
public class Pager<T> {

    private List<T> data;

    private Long total;

    private Long offset;

    private Long limit;
}
