/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbContent;

/**
 * @author zhangleic
 *
 */
public interface TbContentDubboService {

	EasyUiDataGrid listByCategory(long categoryId,int page,int rows);
	
	int insTbContent(TbContent tbContent);

	/**
	* @Title: listByCount
	* @param @param count
	* @param @param isSort
	* @param @return    参数
	* @return List<TbContent>    返回类型
	* @throws
	*/
	List<TbContent> listByCount(int count, Boolean isSort);
	
	
}
