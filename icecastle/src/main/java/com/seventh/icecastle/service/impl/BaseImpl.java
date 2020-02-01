package com.seventh.icecastle.service.impl;

import com.seventh.icecastle.db.BaseDb;
import com.seventh.icecastle.mapper.BaseDAO;
import com.seventh.icecastle.service.IBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseImpl implements IBase {

    @Autowired
    BaseDAO baseDAO;

    @Override
    public void updateBase(String code,String baseNo) {
        baseDAO.updateBase(code,baseNo);
    }

    @Override
    public BaseDb getBaseByNo(String baseNo) {
        return baseDAO.getBaseByNo(baseNo);
    }
}
