/**
 * 
 */
package com.mxnavi.server.ego.manage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mxnavi.server.ego.commons.util.FtpUtil;
import com.mxnavi.server.ego.manage.service.PicUploadService;

/**
 * @author zhangleic
 *
 */
@Service
public class PicUploadServiceImpl implements PicUploadService{

	/* (非 Javadoc)
	* <p>Description: </p>
	* @param filename
	* @return
	* @see com.mxnavi.server.ego.manage.service.PicUploadService#picUpload(org.springframework.web.multipart.MultipartFile)
	*/
	
	
	@Value("${com.mxnavi.img.host}")
	private String host;
	
	@Value("${com.mxnavi.img.port}")
	private int port;
	
	@Value("${com.mxnavi.img.username}")
	private String username;
	
	@Value("${com.mxnavi.img.password}")
	private String password;
	
	@Value("${com.mxnavi.img.basePath}")
	private String basePath;
	
	@Value("${com.mxnavi.img.filePath}")
	private String filePath;
	
	/**
	 * 图片的整体前缀
	 * http://192.168.3.35:80/
	 */
	@Value("${com.mxnavi.img.path}")
	private String path;
	
	
	
	/**
	 * 正常我们原来的service层不处理异常，有异常要抛出，是因为我们想要让spring的声明式事务来处理异常并进行回滚，但是此处并不涉及到数据库操作，没有声明式事务，因此异常也可以直接在service层处理	
	 */
	@Override
	public Map picUpload(MultipartFile uploadFile) throws IOException {
		
		
		Map map = new HashMap<String, Object>();
		
		String realFileName = uploadFile.getOriginalFilename();

//      图片上传到 linux服务器乱码，以后再解决！
/*		System.out.println(realFileName);*/
		

		boolean uploadOk = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, realFileName, uploadFile.getInputStream());

		if(uploadOk){
			map.put("error", 0);
			map.put("url", path + realFileName);
		}else{
			map.put("error", 1);
			map.put("message", "图片上传失败");
		}

		

		return map;
		
	}

	
}
