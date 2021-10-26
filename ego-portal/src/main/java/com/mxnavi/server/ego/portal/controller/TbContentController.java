/**
 * 
 */
package com.mxnavi.server.ego.portal.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mxnavi.server.ego.commons.pojo.BigImage;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.portal.service.TbContentService;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbContentController {
	
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("/content/bigImag")
	public ModelAndView listBigAdvertImage(){
		ModelAndView mv = new ModelAndView("index");
		List<BigImage> list = tbContentServiceImpl.listBigAdvertImage();
	
		mv.addObject("ad1", JsonUtils.objectToJson(list));
		return mv;
		
	}
}
