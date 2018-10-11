package com.ssm.base.util;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

/** 字符串实例处理工具类
 * @author yjx
 *
 */
public class StringUtil extends StringUtils{
	  private static final char SEPARATOR = '_';
	    private static final String CHARSET_NAME = "UTF-8";
		/**
		* <p>Title: xmlEscape</p> 
		* <p>Description: 对xml标签进行转译 </p>
		* @param content 待转译的xml
		* @return 转译后的xml
		 */
		public static String xmlEscape(String content){
			return StringEscapeUtils.escapeXml(content);
		}
		
		/**
		* <p>Title: xmlUnEscape</p> 
		* <p>Description: 对转译后的xml进行还原 </p>
		* @param content 待还原的xml
		* @return 还原后的xml
		 */
		public static String xmlUnEscape(String content){
			return StringEscapeUtils.unescapeXml(content);
		}
		
		/**
		* <p>Title: htmlEscape</p> 
		* <p>Description: 将HTML标签进行转译</p>
		* @param content 带转换的html内容
		* @return 转译后的HTML内容
		 */
		public static String htmlEscape(String content){
			return StringEscapeUtils.escapeHtml4(content);
		}
		
		/**
		* <p>Title: htmlUnEscape</p> 
		* <p>Description: 对转码后的html进行还原 </p>
		* @param content 待还原的html内容
		* @return 还原后的html
		 */
		public static String htmlUnEscape(String content){
			return StringEscapeUtils.unescapeHtml4(content);
		}

		/**
		 * 判断字符串是否为null
		 * @param str 待验证的字符串
		 * @return 如果为NULL返回true，否则返回false
		 */
		public static boolean isNull(String str){
			boolean result = str == null ? true : false;
			return result;
		}
		
		/**
		 * 判断字符串是否不为null
		 * @param str  待验证的字符串
		 * @return 如果为NULL返回false，否则返回true
		 */
		public static boolean isNotNull(String str){
			boolean result = str != null ? true : false;
			return result;
		}
		
		/**
		 * 判断字符串是否为空白
		 * @param str 待验证的字符串
		 * @return 如果为空白字符串或者Null返回true，否则返回false
		 */
		public static boolean isBlank(String str){
			boolean result = false;
			if(isNull(str)){
				result = true;
			} else if("".equals(str.trim())){
				result = true;
			}
			return result;
		}
		
		/**
		 * 判断字符串是否不为空白
		 * @param str 待验证的字符串
		 * @return 不过为空白字符串返回false，否则返回true
		 */
		public static boolean isNotBlank(String str){
			boolean result = true;
			if(isNull(str)){
				result = false;
			} else if("".equals(str.trim())){
				result = false;
			}
			return result;
		}
		
		/**
		* <p>Title: str2HexStr</p> 
		* <p>Description: 将字符串转换为16进制的形式 </p>
		* @param content 待转换的字符串
		* @return 转换后的字符串
		 */
		public static String str2HexStr(final String content) {
			Assert.hasText(content, "content不能为空");

			byte[] b = content.getBytes();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < b.length; i++) {
				String strTmp = Integer.toHexString(b[i]);
				if (strTmp.length() > 2){
					strTmp = strTmp.substring(strTmp.length() - 2);
				}
				sb.append(strTmp);
			}

			return sb.toString();
		}
		
		/**
		* <p>Title: hexStr2Str</p> 
		* <p>Description: 将16进制形式的字符串还原为原文</p>
		* @param hexStrContent 待还原的字符串
		* @param charSet 使用的字符编码
		* @return 转换后的字符原文
		* @throws UnsupportedEncodingException charSet设置错误时将会抛出该异常
		 */
		public static String hexStr2Str(String hexStrContent , String charSet) throws UnsupportedEncodingException{
			Assert.hasText(hexStrContent, "hexStrContent不能为空");
			Assert.hasText(charSet, "charSet不能为空");
			byte[] contByte = new byte[hexStrContent.length() / 2];
			for (int i = 0; i < contByte.length; i++) {
				contByte[i] = (byte)(0xff & Integer.parseInt(hexStrContent.substring(i*2, i*2+2), 16)); 
			}
			return new String(contByte , charSet);
		}
		
		/**
		* <p>Title: base64Encode</p> 
		* <p>Description: 使用base64的方式对二进制数据进行转码</p>
		* @param data 待转码的byte[]
		* @return 转码后的字符串内容
		 */
		public static String base64Encode(byte[] data){
			return Base64.encodeBase64String(data);
		}
		
		/**
		* <p>Title: base64Decode</p> 
		* <p>Description: 将Base64转码后的字符串反转为byte[] </p>
		* @param content 待反转的字符串
		* @return 反转后的byte[]
		 */
		public static byte[] base64Decode(String content){
			return Base64.decodeBase64(content);
		}
	    /**
	     * 转换为字节数组
	     * @param bytes
	     * @return
	     */
	    public static String toString(byte[] bytes){
	    	try {
				return new String(bytes, CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return EMPTY;
			}
	    }
	    /**
	     * 转换为字节数组
	     * @param str
	     * @return
	     */
	    public static byte[] getBytes(String str){
	    	if (str != null){
	    		try {
					return str.getBytes(CHARSET_NAME);
				} catch (UnsupportedEncodingException e) {
					return null;
				}
	    	}else{
	    		return null;
	    	}
	    }
	    
	    /**
		 * 转换为Boolean类型
		 * 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
		 */
		public static Boolean toBoolean(final Object val){
			if (val == null){
				return false;
			}
			return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
		}

	    
	    /**
		 * 如果对象为空，则使用defaultVal值 
		 * 	see: ObjectUtils.toString(obj, defaultVal)
		 * @param obj
		 * @param defaultVal
		 * @return
		 */
	    public static String toString(final Object obj, final String defaultVal) {
	    	 return obj == null ? defaultVal : obj.toString();
	    }
	    
	    /**
	     * 是否包含字符串
	     * @param str 验证字符串
	     * @param strs 字符串组
	     * @return 包含返回true
	     */
	    public static boolean inString(String str, String... strs){
	    	if (str != null){
	        	for (String s : strs){
	        		if (str.equals(trim(s))){
	        			return true;
	        		}
	        	}
	    	}
	    	return false;
	    }
	    
		/**
		 * 替换掉HTML标签方法
		 */
		public static String replaceHtml(String html) {
			if (isBlank(html)){
				return "";
			}
			String regEx = "<.+?>";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(html);
			String s = m.replaceAll("");
			return s;
		}
		
		/**
		 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
		 * @param html
		 * @return
		 */
		public static String replaceMobileHtml(String html){
			if (html == null){
				return "";
			}
			return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
		}
		
		/**
		 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
		 * @param txt
		 * @return
		 */
	/*	public static String toHtml(String txt){
			if (txt == null){
				return "";
			}
			return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
		}
*/
		/**
		 * 缩略字符串（不区分中英文字符）
		 * @param str 目标字符串
		 * @param length 截取长度
		 * @return
		 */
		public static String abbr(String str, int length) {
			if (str == null) {
				return "";
			}
			try {
				StringBuilder sb = new StringBuilder();
				int currentLength = 0;
				for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
					currentLength += String.valueOf(c).getBytes("GBK").length;
					if (currentLength <= length - 3) {
						sb.append(c);
					} else {
						sb.append("...");
						break;
					}
				}
				return sb.toString();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		}
		
		public static String abbr2(String param, int length) {
			if (param == null) {
				return "";
			}
			StringBuffer result = new StringBuffer();
			int n = 0;
			char temp;
			boolean isCode = false; // 是不是HTML代码
			boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
			for (int i = 0; i < param.length(); i++) {
				temp = param.charAt(i);
				if (temp == '<') {
					isCode = true;
				} else if (temp == '&') {
					isHTML = true;
				} else if (temp == '>' && isCode) {
					n = n - 1;
					isCode = false;
				} else if (temp == ';' && isHTML) {
					isHTML = false;
				}
				try {
					if (!isCode && !isHTML) {
						n += String.valueOf(temp).getBytes("GBK").length;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				if (n <= length - 3) {
					result.append(temp);
				} else {
					result.append("...");
					break;
				}
			}
			// 取出截取字符串中的HTML标记
			String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
					"$1$2");
			// 去掉不需要结素标记的HTML标记
			temp_result = temp_result
					.replaceAll(
							"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
							"");
			// 去掉成对的HTML标记
			temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
					"$2");
			// 用正则表达式取出标记
			Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
			Matcher m = p.matcher(temp_result);
		/*	List<String> endHTML = Lists.newArrayList();
			while (m.find()) {
				endHTML.add(m.group(1));
			}
			// 补全不成对的HTML标记
			for (int i = endHTML.size() - 1; i >= 0; i--) {
				result.append("</");
				result.append(endHTML.get(i));
				result.append(">");
			}*/
			return result.toString();
		}
		
		/**
		 * 转换为Double类型
		 */
		public static Double toDouble(Object val){
			if (val == null){
				return 0D;
			}
			try {
				return Double.valueOf(trim(val.toString()));
			} catch (Exception e) {
				return 0D;
			}
		}

		/**
		 * 转换为Float类型
		 */
		public static Float toFloat(Object val){
			return toDouble(val).floatValue();
		}

		/**
		 * 转换为Long类型
		 */
		public static Long toLong(Object val){
			return toDouble(val).longValue();
		}

		/**
		 * 转换为Integer类型
		 */
		public static Integer toInteger(Object val){
			return toLong(val).intValue();
		}
		
		/**
		 * 获得i18n字符串
		 *//*
		public static String getMessage(String code, Object[] args) {
			LocaleResolver localLocaleResolver = (LocaleResolver) SpringCtxHelper.getBeanByType(LocaleResolver.class);
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			Locale localLocale = localLocaleResolver.resolveLocale(request);
			return SpringCtxHelper.getApplicationContext().getMessage(code, args, localLocale);
		}
		*/
		/**
		 * 获得用户远程地址
		 */
		public static String getRemoteAddr(HttpServletRequest request){
			String remoteAddr = request.getHeader("X-Real-IP");
	        if (isNotBlank(remoteAddr)) {
	        	remoteAddr = request.getHeader("X-Forwarded-For");
	        }else if (isNotBlank(remoteAddr)) {
	        	remoteAddr = request.getHeader("Proxy-Client-IP");
	        }else if (isNotBlank(remoteAddr)) {
	        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
	        }
	        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
		}

		/**
		 * 驼峰命名法工具
		 * @return
		 * 		toCamelCase("hello_world") == "helloWorld" 
		 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
		 * 		toUnderScoreCase("helloWorld") = "hello_world"
		 */
	    public static String toCamelCase(String s) {
	        if (s == null) {
	            return null;
	        }

	        s = s.toLowerCase();

	        StringBuilder sb = new StringBuilder(s.length());
	        boolean upperCase = false;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);

	            if (c == SEPARATOR) {
	                upperCase = true;
	            } else if (upperCase) {
	                sb.append(Character.toUpperCase(c));
	                upperCase = false;
	            } else {
	                sb.append(c);
	            }
	        }

	        return sb.toString();
	    }

	    /**
		 * 驼峰命名法工具
		 * @return
		 * 		toCamelCase("hello_world") == "helloWorld" 
		 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
		 * 		toUnderScoreCase("helloWorld") = "hello_world"
		 */
	    public static String toCapitalizeCamelCase(String s) {
	        if (s == null) {
	            return null;
	        }
	        s = toCamelCase(s);
	        return s.substring(0, 1).toUpperCase() + s.substring(1);
	    }
	    
	    /**
		 * 驼峰命名法工具
		 * @return
		 * 		toCamelCase("hello_world") == "helloWorld" 
		 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
		 * 		toUnderScoreCase("helloWorld") = "hello_world"
		 */
	    public static String toUnderScoreCase(String s) {
	        if (s == null) {
	            return null;
	        }

	        StringBuilder sb = new StringBuilder();
	        boolean upperCase = false;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);

	            boolean nextUpperCase = true;

	            if (i < (s.length() - 1)) {
	                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
	            }

	            if ((i > 0) && Character.isUpperCase(c)) {
	                if (!upperCase || !nextUpperCase) {
	                    sb.append(SEPARATOR);
	                }
	                upperCase = true;
	            } else {
	                upperCase = false;
	            }

	            sb.append(Character.toLowerCase(c));
	        }

	        return sb.toString();
	    }
	 
	    /**
	     * 转换为JS获取对象值，生成三目运算返回结果
	     * @param objectString 对象串
	     *   例如：row.user.id
	     *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	     */
	    public static String jsGetVal(String objectString){
	    	StringBuilder result = new StringBuilder();
	    	StringBuilder val = new StringBuilder();
	    	String[] vals = split(objectString, ".");
	    	for (int i=0; i<vals.length; i++){
	    		val.append("." + vals[i]);
	    		result.append("!"+(val.substring(1))+"?'':");
	    	}
	    	result.append(val.substring(1));
	    	return result.toString();
	    }
}
