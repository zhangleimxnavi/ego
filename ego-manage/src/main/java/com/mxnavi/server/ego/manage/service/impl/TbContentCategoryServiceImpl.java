/**
 * 
 */
package com.mxnavi.server.ego.manage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService;
import com.mxnavi.server.ego.manage.service.TbContentCategoryService;
import com.mxnavi.server.ego.pojo.TbContentCategory;

/**
 * @author zhangleic
 *
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{

	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
	
	/* (�� Javadoc)
	* <p>Description:ͨ�� pid��ѯ </p>
	* @param pid
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentCategoryService#selByPid(long)
	*/
	@Override
	public List<TbContentCategory> selByPid(long pid) {
		return tbContentCategoryDubboServiceImpl.selByPid(pid);
	}

	
	/* (�� Javadoc)
	* <p>Description:���� </p>
	* @param tbContentCategory
	* @return
	* @throws Exception
	* @see com.mxnavi.server.ego.manage.service.TbContentCategoryService#insertTbContentCategory(com.mxnavi.server.ego.pojo.TbContentCategory)
	*/
	@Override
	public int insertTbContentCategory(TbContentCategory tbContentCategory) throws Exception{
		
		return tbContentCategoryDubboServiceImpl.insertTbContentCategory(tbContentCategory);
		

		
	}


	/* (�� Javadoc)
	* <p>Description: ������ </p>
	* @param tbContentCategory
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentCategoryService#updTbContentCategory(com.mxnavi.server.ego.pojo.TbContentCategory)
	*/
	@Override
	public int updTbContentCategory(TbContentCategory tbContentCategory) {
		

		
		TbContentCategory tbContentCategorySelected = tbContentCategoryDubboServiceImpl.selById(tbContentCategory.getId());

		List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(tbContentCategorySelected.getParentId());
		System.out.println(list);
		for (TbContentCategory contentCategory : list) {
			
			//������ֹͣ����
			if(tbContentCategory.getName().equals(contentCategory.getName())){
				return 0;

			}
		}
		
		
		return tbContentCategoryDubboServiceImpl.updTbContentCategory(tbContentCategory);	
 
		
	}


	/* (�� Javadoc)
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbContentCategoryService#delByid(long)
	*/
	@Override
	public int delByid(long id) {
		
		return tbContentCategoryDubboServiceImpl.delByid(id);
		
	}

}
