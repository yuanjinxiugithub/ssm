package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.base.util.AjaxResponder;
import com.ssm.domain.User;
import com.ssm.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	 @Resource
	 private UserService userService;
	 
	 @ResponseBody
	 @RequestMapping(value = "/findList")
	 public AjaxResponder findList( HttpServletRequest request,HttpServletResponse response) {
		 
		 AjaxResponder result = null;
		 List<User> list = null;
		try {
			list = userService.findList();
			//MyWebScoket.sendMessage("msg",list); //调用webscoket 返回给页面数据
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  , null);
		}
         return result;
        }
}
