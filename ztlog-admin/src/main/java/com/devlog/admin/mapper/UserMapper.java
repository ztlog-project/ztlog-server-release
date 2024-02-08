package com.devlog.admin.mapper;

import com.devlog.admin.vo.user.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserVo selectUserByNo(Long userNo);
}
