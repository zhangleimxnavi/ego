package com.mxnavi.server.ego.manage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.util.IDUtils;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.manage.service.TbItemService;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;


@Service
public class TbItemServiceImpl implements TbItemService{

	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	

	@Override
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemDubboServiceImpl.selectItemPage(page, rows);
	}



	/* (�� Javadoc)
	* <p>ͨ��key �� status ���� ��Ʒ status: </p>
	* ��֧������ع�����Ȼ��ʾʧ���ˣ������е����� �Ѿ� �����
	* @param ids
	* @param status
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbItemService#updateStatusByPrimarykey(java.lang.String, byte)
	*/
	@Override
	public int updateStatusByPrimarykey(String ids, byte status) {
		
		int index = 0;
		
		String[] idsChar = ids.split(",");
		TbItem tbItem = new TbItem();
		for (String id : idsChar) {
			tbItem.setId(Long.parseLong(id));
			tbItem.setStatus(status);
			index = index + tbItemDubboServiceImpl.updateStatusByPrimarykey(tbItem);	
		}
		
		if (index == idsChar.length){
			return 1;
		}else{
			return 0;
		}
	}



	/* (�� Javadoc)
	* <p>Description: </p>
<p>Description:������Ʒ�������Ʒ����Ʒ��������Ʒ��������3�� </p>
	* @param tbItem
	* @param tbItemDesc
	* @return
	* @throws Exception
	* @see com.mxnavi.server.ego.manage.service.TbItemService#insTbItem(com.mxnavi.server.ego.pojo.TbItem, com.mxnavi.server.ego.pojo.TbItemDesc)
	*/
	@Override
	public int insTbItem(TbItem tbItem, String desc) throws Exception {
		
		Date date = new Date();
		
		tbItem.setId(IDUtils.genItemId());
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDesc.setItemDesc(desc);
		
		return tbItemDubboServiceImpl.insTbItem(tbItem, tbItemDesc);
/*		return 1;*/
	}


	
	
	
	
	
	
}
