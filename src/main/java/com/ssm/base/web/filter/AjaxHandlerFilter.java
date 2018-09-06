package com.ssm.base.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.base.util.AjaxResponder;
import com.ssm.base.util.BaseExceptionUtil;
import com.ssm.base.util.ObjectUtil;
import com.ssm.base.util.StringUtil;
import com.ssm.base.util.WebUtil;


public class AjaxHandlerFilter implements Filter{
	private static final Logger log = LoggerFactory.getLogger(AjaxHandlerFilter.class);

	public static final String DataType_json = "json";
	public static final String DataType_script="script";
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//解决跨域问题
		Map<String , String> headers = WebUtil.header2Map(req);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods","PUT, POST,OPTIONS , GET, HEAD");
		resp.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization, authorization");	
	
		if(ObjectUtil.assertNotNull(headers) && StringUtil.isNotBlank(headers.get("ajax"))){
			log.debug("开始进入ajax处理函数，需要进行_dt与_cb参数验证...");
			Map<String , String> reqArgs = WebUtil.request2Map(req);
			if(ObjectUtil.assertNull(reqArgs) || StringUtil.isBlank(reqArgs.get("_dt"))){
				reqArgs.put("_dt", DataType_json);
			}else if(StringUtil.equals(DataType_script, reqArgs.get("_dt")) && StringUtil.isBlank(reqArgs.get("_cb"))){
				throw new BaseExceptionUtil("缺少必要参数，当_dt取值为script时，请对_cb进行赋值，取值为回调JavaScrip函数名称，入参为JSON对象");
			}
			
			req.setAttribute("datatype", reqArgs.get("_dt"));
			req.setAttribute("callback", reqArgs.get("_cb"));
			
			try {
				chain.doFilter(request, response);
			} catch(BaseExceptionUtil ce){
				log.error(ce.getMessage() , ce);
				processAjaxException(ce.getMessage() , req , resp);
			} catch (Exception e) {
				log.error(e.getMessage() , e);
				processAjaxException(e , req , resp);
			}
			
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	* <p>Title: processAjaxException</p> 
	* <p>Description: 对发生的异常进行处理 </p>
	* @param t
	* @param request
	* @param response
	 */
	private void processAjaxException(Throwable t, HttpServletRequest request , HttpServletResponse response){
		if(t instanceof BaseExceptionUtil){
			processAjaxException(t.getMessage() , request , response);
		}else if(ObjectUtil.assertNotNull(t.getCause())){
			this.processAjaxException(t.getCause(), request, response);
		}else{
			processAjaxException("系统发生未知错误！" , request , response);
		}
	}
	
	/**
	* <p>Title: processAjaxException</p> 
	* <p>Description: 使用错误消息处理</p>
	* @param errorMsg 需要提示的错误信息
	* @param request httpservletrequest对象
	* @param response httpservletresponse对象
	 */
	private void processAjaxException(String errorMsg, HttpServletRequest request , HttpServletResponse response){
		try {
			AjaxResponder ar = AjaxResponder.getErrorInstance(errorMsg);
			request.setAttribute("result", ar);
			request.getRequestDispatcher(responsePage).forward(request, response);
		} catch (Exception e) {
			log.error("服务端跳转发生错误，不能正常处理："+e.getMessage() , e);
		}
	}

	String responsePage = "/response.jsp";
	
	public void init(FilterConfig config) throws ServletException {
		String value = config.getInitParameter("responsePage");
		if(StringUtil.isNotBlank(value)){
			responsePage = value;
			log.info("使用指定的错误信息响应页面：{}" , this.responsePage);
		}
	}

	
}
