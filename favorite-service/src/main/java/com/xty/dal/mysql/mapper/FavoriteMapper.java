package com.xty.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xty.config.MyBaseMapper;
import com.xty.dal.mysql.pojo.FavoritePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper extends MyBaseMapper<FavoritePojo> {
}
