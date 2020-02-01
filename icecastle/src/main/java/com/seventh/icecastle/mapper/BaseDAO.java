package com.seventh.icecastle.mapper;

import com.seventh.icecastle.db.BaseDb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BaseDAO{
    void updateBase(@Param("code")  String code, @Param("baseNo")  String baseNo);

    BaseDb getBaseByNo(@Param("baseNo") String baseNo);
}
