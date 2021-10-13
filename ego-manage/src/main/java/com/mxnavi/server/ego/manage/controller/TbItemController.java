package com.mxnavi.server.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.manage.service.impl.TbItemServiceImpl;

@Controller
public class TbItemController {
	
	@Resource
	private TbItemServiceImpl tbItemServiceImpl;
	

	/**
	 * 
	* @Title: selectItemPage  ��ѯ��Ʒ
	* @param @param page
	* @param @param rows
	* @param @return    ����
	* @return EasyUiDataGrid    ��������
	* @throws
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemServiceImpl.selectItemPage(page, rows);
	}
	
	
	/**
	 * 
	* @Title: deleteByPrimarykey ��Ʒɾ��
	* @param @param ids
	* @param @return    ����
	* @return EgoResult    ��������
	* @throws
	 */
	
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public EgoResult deleteByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)3);
		if(index == 1){
			egoResult.setStatus("200");
		}
		
		return egoResult;
		
	}
	
	
	/**
	 * 
	* @Title: reshelfByPrimarykey  ��Ʒ�ϼ�
	* @param @param ids
	* @param @return    ����
	* @return EgoResult    ��������
	* @throws
	 */
	
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelfByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)1);
		if(index == 1){
			egoResult.setStatus("200");
		}
		
		return egoResult;
		
	}
	
	/**
	 * 
	* @Title: instockByPrimarykey  ��Ʒ�¼�
	* @param @param ids
	* @param @return    ����
	* @return EgoResult    ��������
	* @throws
	 */
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public EgoResult instockByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)2);
		if(index == 1){
			egoResult.setStatus("200");
		}
		
		return egoResult;
		
	}

	
	
	

}
