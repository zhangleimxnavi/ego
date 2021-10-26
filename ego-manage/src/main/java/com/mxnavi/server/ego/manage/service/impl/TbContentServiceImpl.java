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
	
	/* (非 Javadoc)
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
	

	
	/* (非 Javadoc)
	* <p>Description: </p>
	* @param tbContent
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentService#insTbContent(com.mxnavi.server.ego.pojo.TbContent)
	*/
	@Override
	public int insTbContent(TbContent tbContent) {
		
		int index = tbContentDubboServiceImpl.insTbContent(tbContent);
		
		/**
		 * 只有新增的是 大广告图片、redis里存在缓存的key，并且值不为空才进行缓存同步，其余情况都不处理
		 */
		
		//如果新增的是 大广告图片，需要同步缓存
		if(tbContent.getCategoryId() == 89L){
			//存在缓存key,才进行更新缓存操作
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
					 //保证6个
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
