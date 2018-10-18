package com.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.domain.mq.RabbitMessage;
import com.ssm.service.mq.MQProducer;

@Controller
@RequestMapping("/mq")
public class MQTestController {
	 @Resource
	 private MQProducer mQProducer;
	 
	 @RequestMapping("insert")
	 @ResponseBody
	 public boolean insertToMQ(HttpServletRequest request,HttpServletResponse response){
		 String msg = request.getParameter("msg");
		 Map<String,String> map = new HashMap<>();
		 map.put("msg", msg);
		 map.put("hello","RabbitMQ");
		 Map<String,Object> mq = new HashMap<>();
		 mq.put("rule","ck");
		 RabbitMessage message = new RabbitMessage("scada-mq-exchange","ignition_queue",mq,map);
		 mQProducer.sendDataToQueue(message);
		 return true;
	 }
}
