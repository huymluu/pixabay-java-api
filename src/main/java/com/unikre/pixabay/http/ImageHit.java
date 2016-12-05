package com.unikre.pixabay.http;

import lombok.Data;

@Data
public class ImageHit extends Hit {
    private String id_hash;
    private String largeImageURL;
    private String fullHDURL;
    private String imageURL;
    private String vectorURL;
    private String previewURL;
    private String webformatURL;

}
