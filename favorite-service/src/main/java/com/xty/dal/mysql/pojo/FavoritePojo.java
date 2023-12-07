package com.xty.dal.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "favorite")
public class FavoritePojo {
    private Long id;
    private Long userId;
    private Long videoId;
}
