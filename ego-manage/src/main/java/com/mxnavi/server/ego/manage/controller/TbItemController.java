package com.mxnavi.server.ego.manage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.util.FtpUtil;
import com.mxnavi.server.ego.commons.util.IDUtils;
import com.mxnavi.server.ego.manage.service.impl.TbItemServiceImpl;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;
import com.mxnavi.server.ego.redis.dao.RedisDao;
import com.mxnavi.server.ego.redis.dao.impl.RedisDaoImpl;

@Controller
public class TbItemController {
	

	
	@Resource
	private TbItemServiceImpl tbItemServiceImpl;
	

	
	/**
	 * 
	* @Title: selectItemPage  查询商品
	* @param @param page
	* @param @param rows
	* @param @return    参数
	* @return EasyUiDataGrid    返回类型
	* @throws
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemServiceImpl.selectItemPage(page, rows);
	}
	
	
	/**
	 * 
	* @Title: deleteByPrimarykey 商品删除
	* @param @param ids
	* @param @return    参数
	* @return EgoResult    返回类型
	* @throws
	 */
	
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public EgoResult deleteByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = 0;
		try {
			index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)3);
		} catch (Exception e) {
			egoResult.setData("服务器异常");
			e.printStackTrace();
		}

		if(index == 1){
			egoResult.setStatus("200");		
		}
		
		return egoResult;
		
	}
	
	
	/**
	 * 
	* @Title: reshelfByPrimarykey  商品上架
	* @param @param ids
	* @param @return    参数
	* @return EgoResult    返回类型
	* @throws
	 */
	
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelfByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = 0;
		try {
			index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)1);
		} catch (Exception e) {
			egoResult.setData("服务器异常");
			e.printStackTrace();
		} 
		if(index == 1){
			egoResult.setStatus("200");
		}
		
		return egoResult;
		
	}
	
	/**
	 * 
	* @Title: instockByPrimarykey  商品下架
	* @param @param ids
	* @param @return    参数
	* @return EgoResult    返回类型
	* @throws
	 */
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public EgoResult instockByPrimarykey(String ids){
		
		EgoResult egoResult = new EgoResult();
		
		int index = 0;
		try {
			index = tbItemServiceImpl.updateStatusByPrimarykey(ids, (byte)2);
		} catch (Exception e) {
			egoResult.setData("服务器异常");
			e.printStackTrace();
		}
		if(index == 1){
			egoResult.setStatus("200");
		}
		
		return egoResult;
		
	}
	
	

	
	@RequestMapping("/item/save")
	@ResponseBody
	public EgoResult insTbItem(TbItem tbItem,String desc,String itemParams){
			
/*		
		  `created` datetime NOT NULL COMMENT '创建时间',
		  `updated` datetime NOT NULL COMMENT '更新时间',
		  id*/
		
			EgoResult egoResult = new EgoResult();

			
			try {
				int index = tbItemServiceImpl.insTbItem(tbItem, desc,itemParams);
				if(index == 1){
					egoResult.setStatus("200");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				egoResult.setData(e.getMessage());
				
			}
			
			
			return egoResult;
			
		}
	

	
	
	

}
