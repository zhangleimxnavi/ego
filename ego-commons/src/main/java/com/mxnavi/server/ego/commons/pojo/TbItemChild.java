/**
 * 
 */
package com.mxnavi.server.ego.commons.pojo;

import java.util.Arrays;
import java.util.List;

import com.mxnavi.server.ego.pojo.TbItem;

/**
 * @author zhangleic
 *
 */
public class TbItemChild extends TbItem{

	private String [] images;

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "TbItemChild [images=" + Arrays.toString(images) + "]";
	}


	
}
