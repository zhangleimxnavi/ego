/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mxnavi.server.ego.commons.util.FtpUtil;
import com.mxnavi.server.ego.manage.service.PicUploadService;

/**
 * @author zhangleic
 *
 */

@Controller
public class PicUploadController {
	
	/**
	 * 
	* @Title: imgUpload ͼƬ�ϴ�
	* kindEditor������� �����Բ��Ǻܺã����ͼƬ�ϴ�Ӧ��ʹ���������õķ���
	* @param @return    ����
	* @return Map    ��������
	* @throws
	 */
	
/*	com.mxnavi.img.host=192.168.3.35
			com.mxnavi.img.port=21
			com.mxnavi.img.username=ftpuser
			com.mxnavi.img.password=ftpuser
			com.mxnavi.img.basePath=/home/ftpuser
			com.mxnavi.img.filePath=/
			com.mxnavi.img.path=http://192.168.3.35:80/
*/	
	@Resource
	private PicUploadService picUploadServiceImpl;
	
	

	
	
	/**
	 * 
	* @Title: imgUpload
	* �쳣�׳�����Ҫ�� cotrollerʵ���쳣�������
	* @param @param filename
	* @param @return
	* @param @throws IOException    ����
	* @return Map    ��������
	* @throws
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map imgUpload(MultipartFile uploadFile){
		
		Map map = new HashMap<String,Object>();
		
		try {
			map = picUploadServiceImpl.picUpload(uploadFile);
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "ͼƬ�ϴ�ʧ��,�����������쳣�����Ժ�");
		}
		
		return map;	
		
	}
	
	

}
