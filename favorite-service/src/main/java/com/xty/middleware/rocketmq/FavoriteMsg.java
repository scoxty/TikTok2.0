package com.xty.middleware.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMsg {
    private Long userId;
    private Long videoId;
    private Integer actionType;
}
