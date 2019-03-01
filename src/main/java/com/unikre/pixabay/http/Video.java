package com.unikre.pixabay.http;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Video extends Hit {
    private String picture_id;

}
