package com.gcd.attribution.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> content = new ArrayList<>();
    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private boolean last;
}
