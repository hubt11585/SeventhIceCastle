package com.seventh.icecastle.service.impl;

import com.seventh.icecastle.bean.CheckDataResult;
import com.seventh.icecastle.db.UserDb;
import com.seventh.icecastle.dto.LoginDto;
import com.seventh.icecastle.dto.UserDto;
import com.seventh.icecastle.exception.MyException;
import com.seventh.icecastle.mapper.UserDAO;
import com.seventh.icecastle.service.IBase;
import com.seventh.icecastle.service.IUser;
import com.seventh.icecastle.utils.AESUtil;
import com.seventh.icecastle.utils.MD5Util;
import com.seventh.icecastle.utils.MailUtil;
import com.seventh.icecastle.utils.SnowFlake;
import com.seventh.icecastle.utils.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class UserImpl implements IUser {

    @Autowired
    UserDAO userDAO;

    @Autowired
    IBase base;

    @Override
    public String register(UserDto userDto) {
        CheckDataResult checkDataResult = this.userCheck(userDto);
        if(checkDataResult.getResult()){
            userDto.setUuid(SnowFlake.nextId()+"");
            userDAO.register(userDto);
            /******邮箱激活邀请发送*******/
            this.sendMail(userDto);
        }else{
            return checkDataResult.getMsg();
        }
        return "";
    }

    /**
     * 发送邮件
     * @param userDto
     */
    private void sendMail(UserDto userDto) {
        try {
            String iceCastleKey = base.getBaseByNo("IC0001").getBaseValue();
            String context = "http://seventhicecastle.com/user/verification?key=";
            String code = userDto.getUuid();
            context = context + AESUtil.encrypt(code+";"+MD5Util.encryptionByMD5(code+iceCastleKey),iceCastleKey);
            MailUtil.send(userDto.getMail(),context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CheckDataResult userCheck(UserDto userDto) {
        CheckDataResult checkDataResult = new CheckDataResult();
        StringBuffer resultMsg = new StringBuffer();
        if(userDto != null){
            /*********检验字段不能为空*************/
            if(StringUntil.isBlank(userDto.getName())){
                resultMsg.append("昵称不能为空！");
            }
            if(StringUntil.isBlank(userDto.getMail())){
                resultMsg.append("邮箱不能为空！");
            }
            if(StringUntil.isBlank(userDto.getPassWord())){
                resultMsg.append("密码不能为空！");
            }
            if(StringUntil.isBlank(userDto.getConfirmPassWord())){
                resultMsg.append("确认密码不能为空！");
            }
            /*********检验规则 1：邮箱格式*************/
            if(!StringUntil.checkEmail(userDto.getMail())){
                resultMsg.append("邮箱格式不正确！");
            }
            /*********检验规则 2：密码和确认密码不一致*************/
            if(!Objects.equals(userDto.getPassWord(),userDto.getConfirmPassWord())){
                resultMsg.append("邮箱格式不正确！");
            }
        }
        /**校验邮箱是否被注册**/
        UserDb userDb = userDAO.getUserByMail(userDto.getMail());
        if(userDb != null){
            if(Objects.equals(userDb.getStatus(),"1")){
                resultMsg.append("该邮箱已经被注册！");
            }else{
                resultMsg.append("该邮箱已经登记，请继续激活！");
            }
        }

        if(StringUntil.isNotBlank(resultMsg.toString())){
            checkDataResult.setMsg(resultMsg.toString());
            checkDataResult.setResult(false);
        }
        return checkDataResult;
    }

    @Override
    public String verification(String key) {
        String resultMsg = "";
        try {
            String iceCastleKey = base.getBaseByNo("IC0001").getBaseValue();
            String code = key.replaceAll(" ","+");
            code = AESUtil.decrypt(code,iceCastleKey);
            String uuid = code.substring(0,code.indexOf(";"));
            String str1 = MD5Util.encryptionByMD5(uuid+iceCastleKey);
            String str2 = code.substring(code.indexOf(";")+1,code.length());
            if(str1.equals(str2)){
                userDAO.updateUser("1",uuid);
            }else{
                resultMsg = "激活失败，验证信息已经过期!";
            }
        } catch (Exception e) {
            resultMsg = "激活失败！";
        }
        return resultMsg;
    }

    @Override
    public String activate(LoginDto loginDto) {
        String resultMsg = "";
        UserDb userDb = userDAO.getUserByMail(loginDto.getUserName());
        if (userDb != null) {
            UserDto userDto = new UserDto();
            userDto.setUuid(userDb.getUuid());
            userDto.setMail(userDb.getMail());
            /******邮箱激活邀请发送*******/
            this.sendMail(userDto);
        }
        return resultMsg;
    }

    @Override
    public UserDto login(LoginDto loginDto,HttpSession session) {
        UserDto userDto = null;
        UserDb userDb = userDAO.getUserByMail(loginDto.getUserName());
        if(!Objects.equals(userDb.getStatus(),"1")){
            throw new MyException("ICE0003","还未激活，请先激活账号!");
        }else{
            if(!Objects.equals(userDb.getPassWord(),loginDto.getPassWord())){
                throw new MyException("ICE0004","登录失败，密码错误!");
            }else{
                userDto = new UserDto();
                userDto.setMail(userDb.getMail());
                userDto.setSessionId(session.getId()); //将sessionId 传给前台用于cookie。
                userDto.setUuid(userDb.getUuid());
                userDto.setName(userDb.getName());
                session.setAttribute(session.getId(),userDto);
            }
        }
        return userDto;
    }

    @Override
    public UserDb getUser(String uuid) {
        return userDAO.getUserByUuid(uuid);
    }

    @Override
    public String update(UserDto userDto) {
        userDAO.updateUserByUuid(userDto);
        return null;
    }
}
