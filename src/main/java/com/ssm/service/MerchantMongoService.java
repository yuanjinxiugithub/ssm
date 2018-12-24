package com.ssm.service;

import java.util.List;

import com.ssm.domain.Test;
import com.ssm.domain.User;

public interface MerchantMongoService {
    User getById(String id);//根据id获取用户信息
    List<Test> getpiclist(int current,int rowCount,String sortid);//获取一页记录
    int getNums();//获取总数目
    void SaveorUpdatePicture(Test test);
}
