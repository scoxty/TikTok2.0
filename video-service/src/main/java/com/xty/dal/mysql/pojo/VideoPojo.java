package com.xty.dal.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "video")
public class VideoPojo {
    private Long id;
    private Long authorId;
    private String playUrl;
    private String coverUrl;
    private String title;
    private Date createdTime;
    private Date updatedTime;
}
