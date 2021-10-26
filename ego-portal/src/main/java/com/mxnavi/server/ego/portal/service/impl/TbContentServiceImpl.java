/**
 * 
 */
package com.mxnavi.server.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.BigImage;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbContentDubboService;
import com.mxnavi.server.ego.pojo.TbContent;
import com.mxnavi.server.ego.portal.service.TbContentService;
import com.mxnavi.server.ego.redis.dao.RedisDao;
import com.mxnavi.server.ego.redis.dao.impl.RedisDaoImpl;

/**
 * @author zhangleic
 *
 */

@Service
public class TbContentServiceImpl implements TbContentService{

	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	
	@Resource
	private RedisDao redisDaoImpl;
	
	@Value("${com.mxnavi.server.ego.redis.bigImagekey}")
	private String bigImagekey;
	
	/* (�� Javadoc)
	* <p>Description: </p>
	* @return
	* ���ݸ�ʽ
	* [
		  {
		    "srcB": "",
		    "widthB": 550,
		    "heightB": 240,
		    "src": "http://192.168.3.35:80/fsfsg.jpg",
		    "height": 240,
		    "width": 670,
		    "alt": "ѭ��4",
		    "href": "ѭ��4"
		  },
		  {
		    "srcB": null,
		    "widthB": 550,
		    "heightB": 240,
		    "src": "http://localhost:9000/images/2015/07/27/1437979377450366.jpg",
		    "height": 240,
		    "width": 670,
		    "alt": "ad3",
		    "href": "http://www.sina.com.cn"
		  }
		]
	*/
	@Override
	public List<BigImage> listBigAdvertImage() {
		
		List<BigImage> listResp = new ArrayList<BigImage>();
		
		//��� redis�����ݣ�ֱ��ȡ��
		if(redisDaoImpl.exists(bigImagekey)){
			 
			 String value = redisDaoImpl.get(bigImagekey);
			 if(value!=null&&!value.equals("")){
				 listResp = JsonUtils.jsonToList(value, BigImage.class);
			 }
			 
		//��� redis�� ���ݣ��� mysql ȡ������д�� redis
		 }else{
			 
			 List<TbContent> list = tbContentDubboServiceImpl.listByCount(6, true);
				
			 for (TbContent tbContent : list) {
				 BigImage image = new BigImage();
				 image.setSrc(tbContent.getPic());
				 image.setSrcB(tbContent.getPic2());
				 image.setWidth(670);
				 image.setWidthB(550);
				 image.setHeight(240);
				 image.setHeightB(240);
				 image.setHref(tbContent.getUrl());
				 image.setAlt(tbContent.getTitle());
				 
				 listResp.add(image);
			}
			 
			 redisDaoImpl.set(bigImagekey, JsonUtils.objectToJson(listResp));
			 
		 }
		 
		 
		return listResp;
	}

}
