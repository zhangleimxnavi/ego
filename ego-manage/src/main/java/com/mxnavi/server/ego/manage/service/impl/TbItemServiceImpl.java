package com.mxnavi.server.ego.manage.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.manage.service.TbItemService;
import com.mxnavi.server.ego.pojo.TbItem;


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






	



	
	
}
