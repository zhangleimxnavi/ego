/**
 * 
 */
package com.mxnavi.server.ego.manage.pojo;

import com.mxnavi.server.ego.pojo.TbItemParam;

/**
 * @author zhangleic
 *为了不影响整个pojo的类（别人可能也在使用，因此我们不想修改原始的pojo类）
 *我们在manage中 创建了 TbitemParamChild类继承至 TbitemParam类，增加了一个字段来显示 商品类目名称（为了封装了JSP中要的所有数据）
 */
public class TbItemParamChild extends TbItemParam{
	
	private String itemCatName;

	public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	

}
