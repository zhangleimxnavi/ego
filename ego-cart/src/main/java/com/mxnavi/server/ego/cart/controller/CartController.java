/**
 * 
 */
package com.mxnavi.server.ego.cart.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.cart.service.CartService;
import com.mxnavi.server.ego.cart.service.impl.CartServiceImpl;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.pojo.TbItemChild;

/**
 * @author zhangleic
 *
 *
 *
 */

@Controller
public class CartController {
	
	@Resource
	private CartService cartServiceImpl;
	
	
	@RequestMapping("/cart/add/{id}.html")
	public String cartAdd(@PathVariable Long id,int num,HttpServletRequest request){
		cartServiceImpl.cartAdd(id, num, request);
		return "cartSuccess";
	}
	
	
	@RequestMapping("/cart/cart.html")
	public String showCart(Model model,HttpServletRequest request){
		List<TbItemChild> cartList = cartServiceImpl.showCart(request);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	
	@RequestMapping("/cart/update/num/{id}/{num}.action")
	@ResponseBody
	public EgoResult updateCart(Model model,HttpServletRequest request,@PathVariable long id,@PathVariable int num){
		EgoResult result = new EgoResult();
		String status = cartServiceImpl.updateCart(request,id,num);
		if(status.equals("ok")){
			result.setStatus("200");
		}
		return result;
	}
	
	@RequestMapping("/cart/delete/{id}.action")
	@ResponseBody
	public EgoResult delCart(Model model,HttpServletRequest request,@PathVariable long id){
		EgoResult result = new EgoResult();
		String status = cartServiceImpl.delCart(request,id);
		if(status.equals("ok")){
			result.setStatus("200");
		}
		return result;
	}
	
	
	
	
	
	

}
