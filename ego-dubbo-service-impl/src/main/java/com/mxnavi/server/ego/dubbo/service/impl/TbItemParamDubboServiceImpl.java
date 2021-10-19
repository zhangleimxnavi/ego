/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemParamDubboService;
import com.mxnavi.server.ego.mapper.TbItemParamMapper;
import com.mxnavi.server.ego.pojo.TbItemParam;
import com.mxnavi.server.ego.pojo.TbItemParamExample;

/**
 * @author zhangleic
 *
 */
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{
	
	@Resource
	private TbItemParamMapper tbItemParamMapper;

	/* (�� Javadoc)
	* <p>Description:��ѯȫ�������� </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemParamService#listAll()
	*/
	@Override
	public EasyUiDataGrid listAll(int page,int rows) {
		// TODO Auto-generated method stub

		EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
		
		//���÷�ҳ����
		PageHelper.startPage(page, rows);
		//ִ�в�ѯȫ��
		TbItemParamExample example = new TbItemParamExample();
		/**
		 * ���򹤳����ɵķ����У�����withBlobs�ķ��� ���ѯ text���͵��У����򲻻�
		 * ��ѯ text���͵��У���Ϊtext�������ݽ϶࣬����Ӱ���ѯ���ܣ�������ʱ����Ҫ��ѯ 
		 * text���ݣ���ʱ����Ҫ��ѯ����� �ṩ���������� �����ѡ��
		 */
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		System.out.println("����б��ǣ�" + list);
		//�ѷ�ҳ��Ϣ�Ĳ�ѯ�������װ�� PageInfo����
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		
		easyUiDataGrid.setTotal(pageInfo.getTotal());
		easyUiDataGrid.setRows(pageInfo.getList());
		
		return easyUiDataGrid;
	}

}
