package com.seventh.icecastle.service;

import com.seventh.icecastle.db.BaseDb;

public interface IBase {
    void updateBase(String code,String baseNo);


    BaseDb getBaseByNo(String baseNo);
}
