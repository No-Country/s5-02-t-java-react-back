package com.s5.javaback.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private final List<T> data;
    private final long totalElements, currentPage;
}
