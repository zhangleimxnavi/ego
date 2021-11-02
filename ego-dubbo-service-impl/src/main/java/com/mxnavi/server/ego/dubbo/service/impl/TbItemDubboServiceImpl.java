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
		//设置查询条件
		PageHelper.startPage(page, rows);
		//执行查询，获取查询结果
		List<TbItem> lists = tbItemMapper.selectByExample(new TbItemExample());
		//用PageInfo对查询结果进行包装，所有的分页信息都包装到 PageInfo中
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

	
	
	
	
	/* (非 Javadoc)
	* <p>Description:新增商品 </p>
	* @param tbItem
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#insTbItem(com.mxnavi.server.ego.pojo.TbItem)
	*   1、Spring框架下，所有SQL异常都被org.springframework重写为RuntimeException，因此出现Sql异常，事务会发生回滚！
	*   2、此处 我们手动抛出了异常
		这段代码，填加try catch 目的：
		是为了当出错时能够继续运行，然后 把 index 的 值保留下来
		同时既然手动抛异常了，那么 RuntimeException就可以捕获了，然后手动抛出 检查异常
		最后只要判断 index的值 等于2 就执行成功，否则就手动抛出异常
		注意调试阶段 要打印 出异常
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
			throw new Exception("新增失败,发生异常");
		}
		
		
		
		
	}

	/* (非 Javadoc)
	* <p>Description: 查询全部 </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#listAll()
	*/
	@Override
	public List<TbItem> listAll() {
		
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		
		return list;
	}

	
	
	/* (非 Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDubboService#selById()
	*/
	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}


	
	
	
	
	
	
	
}
