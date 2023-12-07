package com.xty.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfo {
    private Long id;
    private UserInfo author;
    private String play_url;
    private String cover_url;
    private Long favorite_count;
    private Long comment_count;
    private boolean is_favorite;
    private String title;
}
