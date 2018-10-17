package ssm.test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yjx
 *   保存图片工具类
 */
public class ImgUtil {
	
     private static final Logger logger = LoggerFactory.getLogger(ImgUtil.class);
	
	/**   
	 * @Title: saveToFile   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param destUrl 原图片地址
	 * @param: @param filePath   图片存储路径   
	 * @return: void      
	 * @throws   
	 */  
	public static void saveToFile(String destUrl,String filePath){
		FileOutputStream fos = null;
		BufferedInputStream bis = null;  
		HttpURLConnection httpUrl = null;  
		URL url = null;
		int BUFFER_SIZE = 1024;  	
		byte[] buf = new byte[BUFFER_SIZE];  	
		int size = 0;  		
		try {  			
			url = new URL(destUrl);  			
			httpUrl = (HttpURLConnection) url.openConnection();  			
			httpUrl.connect();  			
			bis = new BufferedInputStream(httpUrl.getInputStream());  			
			fos = new FileOutputStream(filePath);  			
			while ((size = bis.read(buf)) != -1) {   				
				fos.write(buf, 0, size);  			}  			
			    fos.flush();  
			    logger.info("保存图片成功!");
			} catch (IOException e) {  		
				
			} catch (ClassCastException e) {  		
				
			} finally {  			
				try {  				
					fos.close();  				
					bis.close();  				
					httpUrl.disconnect();  			
				} 
				catch (IOException e) {  			
					
				} catch (NullPointerException e) {  
					
				 }  		
			}  
	}
	
	public static void main(String[] args){
		String str = "http://api.map.baidu.com/staticimage?center=106.720568,26.585137&width=697&height=550&markers=106.729443,26.593795&markerStyles=-1,http://api.map.baidu.com/images/marker_red.png,-1,23,25&zoom=15&labels=106.730143,26.594695&labelStyles=师大某小区包子铺,1,14,0xFFFFFF,0xEC624D,1";		
		String fileName = "b";
		String filePath="E:\\"+fileName+".png";
		ImgUtil.saveToFile(str, filePath);
		
	}
}
