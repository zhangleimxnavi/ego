package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.mapper.TbItemMapper;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemExample;

public class TbItemDubboServiceImpl implements TbItemDubboService{

	@Resource
	private TbItemMapper tbItemMapper;
	
	@Override
	public EasyUiDataGrid selectItemPage(int page,int rows) {
		// TODO Auto-generated method stub
		EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
		//���ò�ѯ����
		PageHelper.startPage(page, rows);
		//ִ�в�ѯ����ȡ��ѯ���
		List<TbItem> lists = tbItemMapper.selectByExample(new TbItemExample());
		//��PageInfo�Բ�ѯ������а�װ�����еķ�ҳ��Ϣ����װ�� PageInfo��
		PageInfo<TbItem> pageInfo = new PageInfo<>(lists);
		
		easyUiDataGrid.setTotal(pageInfo.getTotal());
		easyUiDataGrid.setRows(pageInfo.getList());
		
		return easyUiDataGrid;
	}

	
	
}
