/**
 * 
 */
package com.mxnavi.server.ego.item.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbContentDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.item.service.TbItemService;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.redis.dao.RedisDao;
import com.mxnavi.server.ego.redis.dao.impl.RedisDaoImpl;


/**
 * @author zhangleic
 *
 */
@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Resource
	private RedisDao redisDaoImpl;
	
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.item.service.TbItemService#selItemById()
	*/
	@Override
	public TbItem selItemById(long id) {
		if(redisDaoImpl.exists("item:"+id)){
			String itemSelected = redisDaoImpl.get("item:"+id);
			if(itemSelected!=null&&!itemSelected.equals("")){
				return JsonUtils.jsonToPojo(itemSelected, TbItem.class);
			}
			
		}
			TbItem item =  tbItemDubboServiceImpl.selById(id);
			redisDaoImpl.set("item:"+id, JsonUtils.objectToJson(item));
			return item;
	}

}
