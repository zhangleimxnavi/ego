/**
 * 
 */
package com.mxnavi.server.ego.item.pojo;

import java.util.List;

/**
 * @author zhangleic
 * 此处的查询涉及到：父类别中包含多个子类别的问题
 * 因为原始的 pojo 类 TbItemCat是通过 generater生成的，不包含 list属性，我们没法把查询结果封装到 原始的TbItemCat类中
 * 因此我们只能把 返回结果 封装到 新的类 中
 */
public class TbItemCatMenuResult {
	
	//返回给前台的 商品类别的 菜单 列表
	private List<Object>  data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
}
