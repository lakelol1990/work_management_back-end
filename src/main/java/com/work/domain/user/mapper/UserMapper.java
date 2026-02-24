package com.work.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.work.domain.user.entity.User;

@Mapper
public interface UserMapper {

    User findByEmail(String email);

    void insertUser(User user);   // 추가

    //Long findIdByEmail(String email);
}