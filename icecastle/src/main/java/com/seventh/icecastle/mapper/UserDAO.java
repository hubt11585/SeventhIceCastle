package com.seventh.icecastle.mapper;

import com.seventh.icecastle.db.UserDb;
import com.seventh.icecastle.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    void register(@Param("userDto") UserDto userDto);

    void updateUser(@Param("status") String status, @Param("uuid") String uuid);

    UserDb getUserByMail(@Param("mail") String mail);

    UserDb getUserByUuid(@Param("uuid") String uuid);

    void updateUserByUuid(@Param("userDto") UserDto userDto);
}
