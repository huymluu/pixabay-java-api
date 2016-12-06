package com.unikre.pixabay.http;

import lombok.Data;

import java.util.List;

@Data
public class Result<T extends Hit> {
    private long total;
    private long totalHits;
    private List<T> hits;
}
