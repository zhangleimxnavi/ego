/**
 * 
 */
package com.mxnavi.server.ego.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.BigImage;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbContentDubboService;
import com.mxnavi.server.ego.manage.service.TbContentService;
import com.mxnavi.server.ego.pojo.TbContent;

import com.mxnavi.server.ego.redis.dao.RedisDao;



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
	* @param categoryId
	* @param page
	* @param rows
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentService#listByCategory(long, int, int)
	*/
	@Override
	public EasyUiDataGrid listByCategory(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		return tbContentDubboServiceImpl.listByCategory(categoryId, page, rows);
	}
	

	
	/* (�� Javadoc)
	* <p>Description: </p>
	* @param tbContent
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentService#insTbContent(com.mxnavi.server.ego.pojo.TbContent)
	*/
	@Override
	public int insTbContent(TbContent tbContent) {
		
		int index = tbContentDubboServiceImpl.insTbContent(tbContent);
		
		/**
		 * ֻ���������� ����ͼƬ��redis����ڻ����key������ֵ��Ϊ�ղŽ��л���ͬ�������������������
		 */
		
		//����������� ����ͼƬ����Ҫͬ������
		if(tbContent.getCategoryId() == 89L){
			//���ڻ���key,�Ž��и��»������
			if(redisDaoImpl.exists(bigImagekey)){
				 String value = redisDaoImpl.get(bigImagekey);
				 if(value!=null&&!value.equals("")){
					 List<BigImage> listResp = JsonUtils.jsonToList(value, BigImage.class);
					 
					 BigImage image = new BigImage();
					 image.setSrc(tbContent.getPic());
					 image.setSrcB(tbContent.getPic2());
					 image.setWidth(670);
					 image.setWidthB(550);
					 image.setHeight(240);
					 image.setHeightB(240);
					 image.setHref(tbContent.getUrl());
					 image.setAlt(tbContent.getTitle());
					 //��֤6��
					 if(listResp.size() == 6){
						 listResp.remove(5);
					 }
					 
					 listResp.add(0,image);
					 redisDaoImpl.set(bigImagekey, JsonUtils.objectToJson(listResp));
				 }
			 }	
			
		}
		
		return index;
		
	}

}
