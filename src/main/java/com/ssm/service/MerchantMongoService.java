package com.ssm.service;

import java.util.List;

import com.ssm.domain.Test;

public interface MerchantMongoService {
    List<Test> getpiclist(int current,int rowCount,String sortid);//获取一页记录
    int getpicturenum();//获取总数目
}
