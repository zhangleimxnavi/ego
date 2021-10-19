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
	
	/* (�� Javadoc)
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
			//�������������ͣ��������ѭ���ڲ����ܱ�������tbItemParam��ֵ
			TbItemParamChild child = new TbItemParamChild();
			child.setId(tbItemParam.getId());
			child.setItemCatId(tbItemParam.getItemCatId());
			child.setParamData(tbItemParam.getParamData());
			child.setCreated(tbItemParam.getCreated());
			child.setUpdated(tbItemParam.getUpdated());
			/**
			 * ���ڲ�ѯ�Ľ����û����Ʒ������ƣ�������TbitemCatDubboServiceImpl������ѯ���� ����ѯ����
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
