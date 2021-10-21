/**
 * 
 */
package com.mxnavi.server.ego.item.pojo;

import java.util.List;

/**
 * @author zhangleic
 * 返回给前台的 商品类别的 菜单 列表中的每一个子元素
 * 子元素有两种情况： 
 * 1、如果有子类别，那么就是一个 包含 u、n、i 的 元素
 * 每个子元素的 包含 u n i；其中：
	 * "u":"/products/1.html",
	 * "n":"<a href='/products/1.html'>图书、音像、电子书刊</a>",
	 *  i: 子类别内容
 * 
 * 2、如果没有子类别，那么就是"/products/3.html|电子书" 这种格式
 */
public class TbItemCatMenuNode {
	
	private String u;
	private String n;
	private List<Object> i;
	
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public List<Object> getI() {
		return i;
	}
	public void setI(List<Object> i) {
		this.i = i;
	}
	

}
