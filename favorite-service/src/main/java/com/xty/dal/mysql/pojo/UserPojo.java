package com.xty.dal.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class UserPojo {
    private Long id;
    private String name;
    private String password;
    private String avatar;
    private String signature;
    private String backgroundImage;
    private Date createdTime;
    private Date updatedTime;
}
