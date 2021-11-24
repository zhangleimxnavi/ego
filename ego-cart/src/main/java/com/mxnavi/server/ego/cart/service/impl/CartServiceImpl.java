/**
 * 
 */
package com.mxnavi.server.ego.cart.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.cart.service.CartService;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.pojo.TbItemChild;
import com.mxnavi.server.ego.commons.util.CookieUtils;
import com.mxnavi.server.ego.commons.util.HttpClientUtil;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbUser;
import com.mxnavi.server.ego.redis.dao.RedisDao;
import com.mxnavi.server.ego.redis.dao.impl.RedisDaoImpl;

/**
 * @author zhangleic
 *
 */
@Service
public class CartServiceImpl implements CartService {

	@Resource
	private RedisDao redisDaoImpl;
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	/* (非 Javadoc)
	* <p>Description: 添加购物车</p>
	* @return
	* @see com.mxnavi.server.ego.cart.service.CartService#cartAdd()
	*/
	@Override
	public void cartAdd(Long id,int num,HttpServletRequest request) {
		
		List <TbItemChild> cartList = new ArrayList<TbItemChild>();
		
		
		String cookie = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String response = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookie);
		EgoResult result = JsonUtils.jsonToPojo(response, EgoResult.class);
		

/*		result.getData() 获取到的是 java.util.LinkedHashMap 类型，这个是 jackson 决定的
		System.out.println(result.getData().getClass().getName());*/

		//购物车的key
		String key = "cart:"+((LinkedHashMap)result.getData()).get("username");
		// redis 中 有这个 key
		if(redisDaoImpl.exists(key)){
			String json = redisDaoImpl.get(key);
			if(json != null && !json.equals("")){
				cartList = JsonUtils.jsonToList(json, TbItemChild.class);
				//包含同类商品，数量增加
/*				int HAVE_THE_SAME_ITEM = 0;*/
				for (TbItemChild tbItemChild : cartList) {
					if(id.equals(tbItemChild.getId())){					
						tbItemChild.setNum(tbItemChild.getNum()+num);
						redisDaoImpl.set(key, JsonUtils.objectToJson(cartList));
						return;
					}
				}
			}
		}
		
/*		两种情况下，需要查询数据库，并缓存到 redis中
			1、redis 中 有这个 key,但是不包含同类商品
			2、redis 中 没有 这个key
			*/
		
		//需要查询数据库
		TbItem tbItem = tbItemDubboServiceImpl.selById(id);
		TbItemChild tbItemChild = new TbItemChild();
		tbItemChild.setId(id);
		tbItemChild.setNum(num);
		tbItemChild.setImages(tbItem.getImage().split(",").length>0?tbItem.getImage().split(","):new String [1]);
		tbItemChild.setTitle(tbItem.getTitle());
		tbItemChild.setPrice(tbItem.getPrice());
		
		//缓存到 redis
		cartList.add(tbItemChild);
		redisDaoImpl.set(key, JsonUtils.objectToJson(cartList));
		
	}

	/* (非 Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.cart.service.CartService#showCart()
	*/
	@Override
	public List<TbItemChild> showCart(HttpServletRequest request) {
		List <TbItemChild> cartList = new ArrayList<TbItemChild>();
		
		//获取购物车的key
		String cookie = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String response = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookie);
		EgoResult result = JsonUtils.jsonToPojo(response, EgoResult.class);
		String key = "cart:"+((LinkedHashMap)result.getData()).get("username");
		
		if(redisDaoImpl.exists(key)){
			String json = redisDaoImpl.get(key);
			if(json != null && !json.equals("")){
				cartList = JsonUtils.jsonToList(json, TbItemChild.class);
				return cartList;
			}
		}
		
		
		return null;
	}

	/* (非 Javadoc)
	* <p>Description: </p>
	* @param request
	* @return
	* @see com.mxnavi.server.ego.cart.service.CartService#updateCart(javax.servlet.http.HttpServletRequest)
	*/
	@Override
	public String updateCart(HttpServletRequest request,long id,int num) {
		
		List <TbItemChild> cartList = new ArrayList<TbItemChild>();
		
		//获取购物车的key
		String cookie = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String response = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookie);
		EgoResult result = JsonUtils.jsonToPojo(response, EgoResult.class);
		String key = "cart:"+((LinkedHashMap)result.getData()).get("username");
		
		if(redisDaoImpl.exists(key)){
			String json = redisDaoImpl.get(key);
			if(json != null && !json.equals("")){
				cartList = JsonUtils.jsonToList(json, TbItemChild.class);
				for (TbItemChild tbItemChild : cartList) {
					if(id == tbItemChild.getId()){					
						tbItemChild.setNum(num);
						return redisDaoImpl.set(key, JsonUtils.objectToJson(cartList));
					}
				}

			}
		}
		return "not ok";

	}

	/* (非 Javadoc)
	* <p>Description: </p>
	* @param request
	* @param id
	* @return
	* @see com.mxnavi.server.ego.cart.service.CartService#delCart(javax.servlet.http.HttpServletRequest, long)
	*/
	@Override
	public String delCart(HttpServletRequest request, long id) {
		List <TbItemChild> cartList = new ArrayList<TbItemChild>();
		
		//获取购物车的key
		String cookie = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String response = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookie);
		EgoResult result = JsonUtils.jsonToPojo(response, EgoResult.class);
		String key = "cart:"+((LinkedHashMap)result.getData()).get("username");
		
		int index = 0;
		
		if(redisDaoImpl.exists(key)){
			String json = redisDaoImpl.get(key);
			if(json != null && !json.equals("")){
				cartList = JsonUtils.jsonToList(json, TbItemChild.class);
				for (TbItemChild tbItemChild : cartList) {
					if(id == tbItemChild.getId()){		
						index = cartList.indexOf(tbItemChild);
					}
				}
				cartList.remove(index);
				return redisDaoImpl.set(key, JsonUtils.objectToJson(cartList));
			}
		}
		
		return "not ok";
	}

	
	
}
