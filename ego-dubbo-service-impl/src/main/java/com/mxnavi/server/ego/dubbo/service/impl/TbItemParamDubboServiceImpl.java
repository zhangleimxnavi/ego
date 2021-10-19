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

	/* (非 Javadoc)
	* <p>Description:查询全部规格参数 </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemParamService#listAll()
	*/
	@Override
	public EasyUiDataGrid listAll(int page,int rows) {
		// TODO Auto-generated method stub

		EasyUiDataGrid easyUiDataGrid = new EasyUiDataGrid();
		
		//设置分页条件
		PageHelper.startPage(page, rows);
		//执行查询全部
		TbItemParamExample example = new TbItemParamExample();
		/**
		 * 逆向工程生成的方法中，带有withBlobs的方法 会查询 text类型的列，否则不会
		 * 查询 text类型的列（因为text类型内容较多，容易影响查询性能，我们有时候需要查询 
		 * text内容，有时候不需要查询，因此 提供了两个方法 供灵活选择）
		 */
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		System.out.println("这个列表是：" + list);
		//把分页信息的查询结果，封装到 PageInfo类中
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		
		easyUiDataGrid.setTotal(pageInfo.getTotal());
		easyUiDataGrid.setRows(pageInfo.getList());
		
		return easyUiDataGrid;
	}

	
	/* (非 Javadoc)
	* <p>Description:通过id删除 </p>
	* @param id
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemParamDubboService#delById(int)
	*/
	@Override
	public int delByIds(String [] ids) throws Exception {

		int index = 0;
		
		
		for (String id : ids) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		
		
		if(index == ids.length){
			return 1;
		}else{
			throw new Exception("删除失败，请不要重复删除，回滚所有删除内容");
		}
		
	}


	/* (非 Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemParamDubboService#selByCid()
	*/
	@Override
	public List<TbItemParam> selByCid(long cid) {
		
		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(cid);
		List<TbItemParam> lists  = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		return lists;
	}


	/* (非 Javadoc)
	* <p>Description:新增商品类别对应的参数模板 </p>
	* @param tbItemParam
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemParamDubboService#insTbItemParam(com.mxnavi.server.ego.pojo.TbItemParam)
	*/
	@Override
	public int insTbItemParam(TbItemParam tbItemParam) {
		return tbItemParamMapper.insertSelective(tbItemParam);
	}

}
