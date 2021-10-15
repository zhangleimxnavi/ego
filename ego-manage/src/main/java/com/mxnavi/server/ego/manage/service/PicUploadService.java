/**
 * 
 */
package com.mxnavi.server.ego.manage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangleic
 *
 */
public interface PicUploadService {

	Map picUpload(MultipartFile filename) throws IOException;
	
}
