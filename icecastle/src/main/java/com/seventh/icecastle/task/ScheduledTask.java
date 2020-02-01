package com.seventh.icecastle.task;

import com.seventh.icecastle.service.IBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@EnableAsync
@Component
public class ScheduledTask {

    @Autowired
    IBase base;

    /**
     * 自动更新秘钥
     */
    @Scheduled(initialDelay = 15 * 1000, fixedDelay = 5*60*1000)
    public void autoGenCode () {
        try {
            String code = Base64.getEncoder().encodeToString((Math.random()+"").getBytes("UTF-8"));
            base.updateBase(code,"IC0001");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
