/**
 * 
 */
package com.mxnavi.server.ego.manage.service;

import java.util.List;

import com.mxnavi.server.ego.pojo.TbContentCategory;

/**
 * @author zhangleic
 *
 */
public interface TbContentCategoryService {

	List<TbContentCategory> selByPid(long pid);
	
	public int insertTbContentCategory(TbContentCategory tbContentCategory) throws Exception;
	
	public int updTbContentCategory(TbContentCategory tbContentCategory);
	
	public int delByid(long id);
}
