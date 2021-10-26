/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbContentDubboService;
import com.mxnavi.server.ego.mapper.TbContentMapper;
import com.mxnavi.server.ego.pojo.TbContent;
import com.mxnavi.server.ego.pojo.TbContentExample;

/**
 * @author zhangleic
 *
 */
public class TbContentDubboServiceImpl implements TbContentDubboService{
	
	@Resource
	private TbContentMapper tbContentMapper;
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param tbContent
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentDubboService#insTbContent(com.mxnavi.server.ego.pojo.TbContent)
	*/
	@Override
	public int insTbContent(TbContent tbContent) {
		
		return tbContentMapper.insertSelective(tbContent);

	}
	
	
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param categoryId
	* @param page
	* @param rows
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentDubboService#listByCategory(int, int, int)
	*/
	@Override
	public EasyUiDataGrid listByCategory(long categoryId, int page, int rows) {
		
		EasyUiDataGrid dataGrid = new EasyUiDataGrid();
		
		PageHelper.startPage(page, rows);
		
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		
		dataGrid.setTotal(pageInfo.getTotal());
		dataGrid.setRows(pageInfo.getList());
		
		return dataGrid;
	}
	
	
	
	@Override
	public List<TbContent> listByCount(int count,Boolean isSort) {
		

		PageHelper.startPage(1, count);
		
		TbContentExample example = new TbContentExample();
		
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		
		example.createCriteria().andCategoryIdEqualTo(89L);
		
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);


		return list;
	}


	
	
}
