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

	/* (�� Javadoc)
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
	 * ͼƬ������ǰ׺
	 * http://192.168.3.35:80/
	 */
	@Value("${com.mxnavi.img.path}")
	private String path;
	
	
	@Override
	public Map picUpload(MultipartFile uploadFile) throws IOException {
		
		
		Map map = new HashMap<Object, Object>();
		
		String realFileName = uploadFile.getOriginalFilename();

//      ͼƬ�ϴ��� linux���������룬�Ժ��ٽ����
/*		System.out.println(realFileName);*/
		

		boolean uploadOk = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, realFileName, uploadFile.getInputStream());

		if(uploadOk){
			map.put("error", 0);
			map.put("url", path + realFileName);
		}else{
			map.put("error", 1);
			map.put("message", "ͼƬ�ϴ�ʧ��");
		}

		

		return map;
		
	}

	
}
