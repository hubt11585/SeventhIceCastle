package com.seventh.icecastle.service;

import com.seventh.icecastle.bean.CheckDataResult;
import com.seventh.icecastle.db.UserDb;
import com.seventh.icecastle.dto.LoginDto;
import com.seventh.icecastle.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface IUser {
    String register(UserDto userDto);

    CheckDataResult userCheck(UserDto userDto);

    String verification(String key);

    String activate(LoginDto loginDto);

    UserDto login(LoginDto loginDto,HttpSession session);

    UserDb getUser(String uuid);

    String update(UserDto userDto);
}
