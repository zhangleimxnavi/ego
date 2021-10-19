/**
 * 
 */
package com.mxnavi.server.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemParamDubboService;
import com.mxnavi.server.ego.manage.pojo.TbItemParamChild;
import com.mxnavi.server.ego.manage.service.TbItemParamService;
import com.mxnavi.server.ego.pojo.TbItemCat;
import com.mxnavi.server.ego.pojo.TbItemParam;

/**
 * @author zhangleic
 *
 */
@Service
public class TbItemParamServiceImpl implements TbItemParamService{
	
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	
	/* (非 Javadoc)
	* <p>Description: </p>
	* @param page
	* @param rows
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbItemParamService#listAll(int, int)
	*/
	@Override
	public EasyUiDataGrid listAll(int page, int rows) {
		
		EasyUiDataGrid easyUiDataGrid = tbItemParamDubboServiceImpl.listAll(page, rows);
		List<TbItemParam> lists = (List<TbItemParam>) easyUiDataGrid.getRows();
		
		
		List<TbItemParamChild> childLists = new ArrayList<TbItemParamChild>();

		
		
		for (TbItemParam tbItemParam : lists) {
			//对象是引用类型，必须放在循环内部才能保留各个tbItemParam的值
			TbItemParamChild child = new TbItemParamChild();
			child.setId(tbItemParam.getId());
			child.setItemCatId(tbItemParam.getItemCatId());
			child.setParamData(tbItemParam.getParamData());
			child.setCreated(tbItemParam.getCreated());
			child.setUpdated(tbItemParam.getUpdated());
			/**
			 * 由于查询的结果中没有商品类别名称，我们在TbitemCatDubboServiceImpl新增查询方法 ，查询出来
			 */
			TbItemCat tbItemCat = tbItemCatDubboServiceImpl.selTbItemCatById(tbItemParam.getItemCatId());
			child.setItemCatName(tbItemCat.getName());
			
			System.out.println("child"+child);
			
			childLists.add(child);
		}
			
		easyUiDataGrid.setRows(childLists);
		
		return easyUiDataGrid;
	}
	
	

}
