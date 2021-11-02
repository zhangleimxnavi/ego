package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.mapper.TbItemDescMapper;
import com.mxnavi.server.ego.mapper.TbItemMapper;
import com.mxnavi.server.ego.mapper.TbItemParamItemMapper;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;
import com.mxnavi.server.ego.pojo.TbItemExample;
import com.mxnavi.server.ego.pojo.TbItemParamItem;

public class TbItemDubboServiceImpl implements TbItemDubboService{

	@Resource
	private TbItemMapper tbItemMapper;
	
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	
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

	@Override
	public int updateStatusByPrimarykey(TbItem tbItem) {
		// TODO Auto-generated method stub
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	
	
	
	
	/* (�� Javadoc)
	* <p>Description:������Ʒ </p>
	* @param tbItem
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#insTbItem(com.mxnavi.server.ego.pojo.TbItem)
	*   1��Spring����£�����SQL�쳣����org.springframework��дΪRuntimeException����˳���Sql�쳣������ᷢ���ع���
	*   2���˴� �����ֶ��׳����쳣
		��δ��룬���try catch Ŀ�ģ�
		��Ϊ�˵�����ʱ�ܹ��������У�Ȼ�� �� index �� ֵ��������
		ͬʱ��Ȼ�ֶ����쳣�ˣ���ô RuntimeException�Ϳ��Բ����ˣ�Ȼ���ֶ��׳� ����쳣
		���ֻҪ�ж� index��ֵ ����2 ��ִ�гɹ���������ֶ��׳��쳣
		ע����Խ׶� Ҫ��ӡ ���쳣
	*/
	@Override
	public int insTbItem(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws Exception {
		
		int index = 0;
		
		try {
			index = tbItemMapper.insertSelective(tbItem);
			index += tbItemDescMapper.insertSelective(tbItemDesc);
			index += tbItemParamItemMapper.insertSelective(tbItemParamItem);
		} catch (Exception e) {
/*			e.printStackTrace();*/
		}

		
		if(index == 3){
			return 1;
			
		}else{
			throw new Exception("����ʧ��,�����쳣");
		}
		
		
		
		
	}

	/* (�� Javadoc)
	* <p>Description: ��ѯȫ�� </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#listAll()
	*/
	@Override
	public List<TbItem> listAll() {
		
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		
		return list;
	}

	
	
	/* (�� Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#selById()
	*/
	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}


	
	
	
	
	
	
	
}
