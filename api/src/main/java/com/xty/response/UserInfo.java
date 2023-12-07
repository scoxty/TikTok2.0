package com.xty.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String name;
    private Long follow_count;
    private Long follower_count;
    private Boolean is_follow;
    private String avatar;
    private String background_image;
    private String signature;
    private Long total_favorited;
    private Long work_count;
    private Long favorite_count;
}
