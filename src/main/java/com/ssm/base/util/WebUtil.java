package com.ssm.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class WebUtil {
	private static final Logger log = LoggerFactory.getLogger(WebUtil.class);

	/**
	* <p>Title: request2Map</p> 
	* <p>Description: 将请求中的参数封装至Map String,String 对象中 </p>
	* @param request HttpServletRequest
	* @return 请求中的Parameter
	 */
	public static Map<String,String> request2Map(HttpServletRequest request){
		log.debug("将request中的参数转为Map...");
		Enumeration<String> keys = request.getParameterNames();
		if(ObjectUtil.assertNull(keys)){
			log.debug("未从request中获取到任何参数名称，返回Null。");
			return null;
		}
		Map<String,String> result = new HashMap<String, String>();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value = ArraysUtil.toString(request.getParameterValues(key),null);
			log.debug("request arg , key : {} ; value : {} ",key,value);
			result.put(key, value);
		}
		return result;
	}
	
	/**
	* <p>Title: header2Map</p> 
	* <p>Description: 将请求中的header封装至Map&lt;String,String&gt;对象中</p>
	* @param request HttpServletRequest
	* @return request中的Head
	 */
	public static Map<String,String> header2Map(HttpServletRequest request){
		log.debug("开始获取HttpServletRequest对象中header数据...");
		if(ObjectUtil.assertNull(request)) return null;
		Enumeration<String> headerKeys = request.getHeaderNames();
		Map<String,String> result = new HashMap<String, String>();
		while(headerKeys.hasMoreElements()){
			String key = headerKeys.nextElement();
			String value = request.getHeader(key);
			log.debug("request head , name = {} ; value = {}" , key , value); 
			result.put(key, value);
		}
		return result;
	}
	
	/**
	* <p>Title: getUrlParams</p> 
	* <p>Description: 将URL中参数格式化为MAP&lt;String,String&gt;类型 </p>
	* @param paramStr 参数串
	* @return 
	 */
	public static Map<String, String> getUrlParams(String paramStr){
		Map<String , String> datas = new HashMap<String, String>();
		//判断是否为空
		if(StringUtil.isBlank(paramStr)){
			return datas;
		}
		//对参数进行拆分
		String[] params = paramStr.split("&");
		for(String param : params){
			String[] arg = param.split("=");
			if(arg.length == 2){
				datas.put(arg[0], arg[1]);
			}
		}
		return datas;
	}
	
	/**
	* <p>Title: getUrlParamsByMap</p> 
	* <p>Description: 将Map类型的数据转为URL参数形式 </p>
	* @param datas 待转换的对象
	* @return
	 */
	public static String getUrlParamsByMap(Map<String , String> datas){
		StringBuilder sb = new StringBuilder("");
		//如果datas中没有数据，返回""
		if(null == datas || datas.size() == 0){
			return sb.toString();
		}
		
		String[] args = new String[datas.size()];
		int i=0;
		for(Map.Entry<String, String> enty : datas.entrySet()){
			args[i] = enty.getKey()+"="+enty.getValue();
			i++;
		}
		return ArraysUtil.toString(args, "&");
	}
	
	public static void main(String[] args) {
		String params = "name=zhangsan&pass=zhangsan123&code=12345";
		Map<String,String> datas = WebUtil.getUrlParams(params);
		String result = WebUtil.getUrlParamsByMap(datas);
		System.out.println(result);
	}
	
	/**
	* <p>Title: AjaxResponse</p> 
	* <p>Description: 针对SpringMVC中返回ModelAndView的ajax的响应做封装处理 </p>
	* @param view 将要跳转的视图
	* @param resp 需要携带的数据
	* @return
	 */
	public static ModelAndView AjaxResponse(String view , AjaxResponder resp){
		ModelAndView mv = new ModelAndView(view);
		mv.addObject("result", resp);
		return mv;
	}
	
    public static String getRequestUri(HttpServletRequest request) {
  String       uri = request.getRequestURI();
        return normalize(decodeAndCleanUriString(request, uri));
    }
    public static String normalize(String path) {
        return normalize(path, true);
    }
    private static String normalize(String path, boolean replaceBackSlash) {

        if (path == null)
            return null;

        // Create a place for the normalized path
        String normalized = path;

        if (replaceBackSlash && normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');

        if (normalized.equals("/."))
            return "/";

        // Add a leading "/" if necessary
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Return the normalized path that we have completed
        return (normalized);

    }
    private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
        uri = decodeRequestString(request, uri);
        int semicolonIndex = uri.indexOf(';');
        return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
    }
    @SuppressWarnings({"deprecation"})
    public static String decodeRequestString(HttpServletRequest request, String source) {
        String enc = determineEncoding(request);
        try {
            return URLDecoder.decode(source, enc);
        } catch (UnsupportedEncodingException ex) {
            if (log.isWarnEnabled()) {
                log.warn("Could not decode request string [" + source + "] with encoding '" + enc +
                        "': falling back to platform default encoding; exception message: " + ex.getMessage());
            }
            return URLDecoder.decode(source);
        }
    }
    public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    protected static String determineEncoding(HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = DEFAULT_CHARACTER_ENCODING;
        }
        return enc;
    }
    /**
     * web.xml配置这个listener即可使用
    * org.springframework.web.context.request.RequestContextListener 
    * <p>Title: getHttpRequest</p> 
    * <p>Description: TODO(这里用一句话描述这个方法的作用) </p>
    * @return
     */
	public static HttpServletRequest getHttpHttpServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	public static HttpServletResponse getHttpServletResponse() {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}

	public static HttpSession getHttpSession() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getSession();
	}
 
}
