package com.unikre.pixabay.http;

import lombok.Data;

@Data
public class Hit {
    private String id;
    private String pageURL;
    private Long views;
    private Long downloads;
    private Long favorites;
    private Long likes;
    private Long comments;
    private String user_id;
    private String user;
    private String userImageURL;
}
