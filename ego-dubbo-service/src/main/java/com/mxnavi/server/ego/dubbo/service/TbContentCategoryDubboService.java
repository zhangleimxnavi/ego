/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.pojo.TbContentCategory;

/**
 * @author zhangleic
 *
 */
public interface TbContentCategoryDubboService {
	
	List<TbContentCategory> selByPid(long pid);
	
	TbContentCategory selById(long id);
	
	int insertTbContentCategory(TbContentCategory tbContentCategory) throws Exception;
	
	int updTbContentCategory(TbContentCategory tbContentCategory);
	

	/**
	* @Title: delByid
	* @param @param id
	* @param @return    ����
	* @return int    ��������
	* @throws
	*/
	int delByid(long id);
}
