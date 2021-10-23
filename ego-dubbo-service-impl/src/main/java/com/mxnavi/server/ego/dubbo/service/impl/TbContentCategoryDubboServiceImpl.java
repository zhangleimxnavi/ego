/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService;
import com.mxnavi.server.ego.mapper.TbContentCategoryMapper;
import com.mxnavi.server.ego.pojo.TbContentCategory;
import com.mxnavi.server.ego.pojo.TbContentCategoryExample;

/**
 * @author zhangleic
 *
 */
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService{

	@Resource
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	/* (�� Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentCategoryService#selByPid()
	*/
	@Override
	public List<TbContentCategory> selByPid(long pid) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbContentCategoryMapper.selectByExample(example);
	}

	
	
	/* (�� Javadoc)
	* <p>Description: ������� </p>
	* @param tbContentCategory
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService#insertTbContentCategory(com.mxnavi.server.ego.pojo.TbContentCategory)
	*/
	@Override
	public int insertTbContentCategory(TbContentCategory tbContentCategory) throws Exception{
		
		
		int index = 0;
		
		boolean isDuplicated = false;
		
		List<TbContentCategory> list = this.selByPid(tbContentCategory.getParentId());
		for (TbContentCategory contentCategory : list) {
			//������ֹͣ����
			if(tbContentCategory.getName().equals(contentCategory.getName())){
				isDuplicated = true;
			}
		}
		
		if(!isDuplicated){
			index += tbContentCategoryMapper.insertSelective(tbContentCategory);
			
			TbContentCategory parent = new TbContentCategory();
			parent.setId(tbContentCategory.getParentId());
			parent.setIsParent(true);;
			
			index += this.updTbContentCategory(parent);
		}
		
		if(index == 2){
			return 1;
		}else{
			throw new Exception("�˵��ظ����޷����");
		}

		
	

	}



	/* (�� Javadoc)
	* <p>Description: �޸� </p>
	* @param tbContentCategory
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService#updTbContentCategory(com.mxnavi.server.ego.pojo.TbContentCategory)
	*/
	@Override
	public int updTbContentCategory(TbContentCategory tbContentCategory) {
		return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
	}



	/* (�� Javadoc)
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService#selById(long)
	*/
	@Override
	public TbContentCategory selById(long id) {
		
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}



	/* (�� Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbContentCategoryDubboService#delByid()
	*/
	@Override
	public int delByid(long id) {

		TbContentCategory tbContentCategorySelected = this.selById(id);

		System.out.println(tbContentCategorySelected);

		List<TbContentCategory> list = this.selByPid(tbContentCategorySelected.getParentId());
		System.out.println("list:" + list);
		
		if(!(list != null || list.size()>0)){

			TbContentCategory tempCategory = new TbContentCategory();
			tempCategory.setIsParent(false);
			tempCategory.setId(id);
			this.updTbContentCategory(tempCategory);
		}
		
		//������ɾ���������޷�ͨ����id��ѯ
		return tbContentCategoryMapper.deleteByPrimaryKey(id);
		

		
		
		
	}
	
	
	
	

}
