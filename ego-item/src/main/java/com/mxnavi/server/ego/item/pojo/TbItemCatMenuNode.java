/**
 * 
 */
package com.mxnavi.server.ego.item.pojo;

import java.util.List;

/**
 * @author zhangleic
 * ���ظ�ǰ̨�� ��Ʒ���� �˵� �б��е�ÿһ����Ԫ��
 * ��Ԫ������������� 
 * 1��������������ô����һ�� ���� u��n��i �� Ԫ��
 * ÿ����Ԫ�ص� ���� u n i�����У�
	 * "u":"/products/1.html",
	 * "n":"<a href='/products/1.html'>ͼ�顢���񡢵����鿯</a>",
	 *  i: ���������
 * 
 * 2�����û���������ô����"/products/3.html|������" ���ָ�ʽ
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
