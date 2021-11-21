package com.evil.inc.icresco.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleBookResponse {
    private List<GoogleBookItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoogleBookItem {
        private GoogleVolumeInfo volumeInfo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoogleVolumeInfo {
        private String title;
        private List<String> authors;
        private String description;
        private ImageLink imageLinks;
        private int pageCount = 1;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageLink {
        private String smallThumbnail;
        private String thumbnail = "https://iconarchive.com/download/i99350/dtafalonso/android-lollipop/Dictionary.ico";
    }

}

