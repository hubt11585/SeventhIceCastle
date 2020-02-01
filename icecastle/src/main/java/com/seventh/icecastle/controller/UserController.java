package com.seventh.icecastle.controller;

import com.seventh.icecastle.bean.ServerResponse;
import com.seventh.icecastle.dto.LoginDto;
import com.seventh.icecastle.dto.UserDto;
import com.seventh.icecastle.service.IUser;
import com.seventh.icecastle.utils.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    IUser iUser;

    @PostMapping(value = "/user/register")
    @ResponseBody
    public ServerResponse register(@RequestBody UserDto userDto){
        ServerResponse serverResponse = new ServerResponse();
        String resultMsg = iUser.register(userDto);
        if(StringUntil.isNotBlank(resultMsg)){
            serverResponse.setMsg(resultMsg);
            serverResponse.setResult(false);
            serverResponse.setCode("ICE0001");
        } else {
            serverResponse.setMsg("注册成功，激活邮件已经发送到您的邮箱，请及时处理！");
        }
        return serverResponse;
    }

    @PostMapping(value = "/user/activate")
    @ResponseBody
    public ServerResponse activate(@RequestBody LoginDto loginDto){
        ServerResponse serverResponse = new ServerResponse();
        String resultMsg = iUser.activate(loginDto);
        if(StringUntil.isNotBlank(resultMsg)){
            serverResponse.setMsg(resultMsg);
            serverResponse.setResult(false);
            serverResponse.setCode("ICE0002");
        } else {
            serverResponse.setMsg("激活成功！");
        }
        return serverResponse;
    }

    @RequestMapping(value = "/user/verification")
    @ResponseBody
    public String verification(@RequestParam String key){
        String resultMsg = iUser.verification(key);
        if(StringUntil.isBlank(resultMsg)){
            resultMsg = "激活成功！";
        }
        return resultMsg;
    }

    @RequestMapping(value = "/user/login")
    @ResponseBody
    public ServerResponse login(@RequestBody LoginDto loginDto,HttpSession session){
        ServerResponse serverResponse = new ServerResponse();
        UserDto userDto = iUser.login(loginDto,session);
        if(userDto != null){
            serverResponse.setMsg("登录成功");
            serverResponse.setData(userDto);
        }
        return serverResponse;
    }


    @RequestMapping(value = "/user/data")
    @ResponseBody
    public ServerResponse userData(@RequestParam String uuid){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setData(iUser.getUser(uuid));
        return serverResponse;
    }

    @PostMapping(value = "/user/update")
    @ResponseBody
    public ServerResponse update(@RequestBody UserDto userDto){
        ServerResponse serverResponse = new ServerResponse();
        String resultMsg = iUser.update(userDto);
        if(StringUntil.isNotBlank(resultMsg)){
            serverResponse.setMsg(resultMsg);
            serverResponse.setResult(false);
            serverResponse.setCode("ICE0005");
        } else {
            serverResponse.setMsg("修改成功！");
        }
        return serverResponse;
    }
}
