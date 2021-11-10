/**
 * 
 */
package com.mxnavi.server.ego.search.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.mxnavi.server.ego.pojo.TbItem;

/**
 * @author zhangleic
 *
 */
public interface TbItemService {
	
	void initItemSolr() throws SolrServerException, IOException;
	
	
	/**
	* @Title: itemSearch
	* @param @param q
	* @param @param model
	* @param @return
	* @param @throws SolrServerException
	* @param @throws IOException    参数
	* @return Model    返回类型
	* @throws
	*/
	Map itemSearch(String q,int page,int rows) throws SolrServerException, IOException;
	
	int addSolrItem(String desc,Map tbItemMap) throws SolrServerException, IOException;
	
}
