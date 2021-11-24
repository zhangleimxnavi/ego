/**
 * 
 */
package com.mxnavi.server.ego.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mxnavi.server.ego.commons.pojo.TbItemChild;

/**
 * @author zhangleic
 *
 */
public interface CartService {
	
	void cartAdd(Long id,int num,HttpServletRequest request);
	
	List<TbItemChild> showCart(HttpServletRequest request);

	/**
	* @Title: updateCart
	* @param @param request
	* @param @return    ����
	* @return List<TbItemChild>    ��������
	* @throws
	*/
	String updateCart(HttpServletRequest request,long id,int num);

	/**
	* @Title: delCart
	* @param @param request
	* @param @param id
	* @param @return    ����
	* @return String    ��������
	* @throws
	*/
	String delCart(HttpServletRequest request, long id);
	
}
