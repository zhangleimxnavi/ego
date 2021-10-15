/**
 * 
 */
package com.mxnavi.server.ego.manage.service;

import java.util.List;

import com.mxnavi.server.ego.pojo.TbItemCat;

/**
 * @author zhangleic
 *
 */
public interface TbItemCatService {
	
	List<TbItemCat> selTbItemCatByPid(long pid);

}
