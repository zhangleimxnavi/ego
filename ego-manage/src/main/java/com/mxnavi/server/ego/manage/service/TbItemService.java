package com.mxnavi.server.ego.manage.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;

public interface TbItemService {
	
	EasyUiDataGrid selectItemPage(int page,int rows);
	
	int updateStatusByPrimarykey(String ids,byte status) throws SolrServerException, IOException;
	

	int insTbItem(TbItem tbItem, String desc,String itemParams) throws Exception;
	
}
