package com.xty.middleware.rocketmq.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMsg {
    private Long userId;
    private String title;
    private String localVideoPath;
}
