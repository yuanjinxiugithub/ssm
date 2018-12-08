package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.domain.DataGrid;
import com.ssm.domain.Test;
import com.ssm.domain.User;
import com.ssm.service.MerchantMongoService;


@Controller
@RequestMapping(value="/mongodb")
public class MerchatMonogController {
 @Autowired
 private MerchantMongoService merchantMongoService;
 
 @RequestMapping(value="/findList")
 @ResponseBody
 public DataGrid<Test> getpiclist(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount
         ,@RequestParam(required=false,value="sort[id]")String sortid
         ,@RequestParam(required=false,value="searchPhrase")String searchPhrase){
     List<Test> list=merchantMongoService.getpiclist(current,rowCount,sortid);
   /*  int total=merchantMongoService.getpicturenum();*/
     DataGrid<Test> grid=new DataGrid<Test>();
     grid.setCurrent(current);
     grid.setRowCount(rowCount);
     grid.setRows(list);
     grid.setTotal(112);
     return grid;
     /*}else{
         List<Picture> list=mongodbService.getsearchresult(current, rowCount, sortid, searchPhrase);
         int total=mongodbService.getsearchresulttotal(searchPhrase);
         DataGrid<Picture> grid=new DataGrid<Picture>();
         grid.setCurrent(current);
         grid.setRowCount(rowCount);
         grid.setRows(list);
         grid.setTotal(total);
         return grid;
     }*/
 }
 
}