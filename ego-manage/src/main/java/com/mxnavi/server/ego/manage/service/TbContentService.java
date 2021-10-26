/**
 * 
 */
package com.mxnavi.server.ego.manage.service;


import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbContent;

/**
 * @author zhangleic
 *
 */
public interface TbContentService {

	EasyUiDataGrid listByCategory(long categoryId,int page,int rows);
	
	int insTbContent(TbContent tbContent);


}
