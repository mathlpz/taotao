package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	/**
	 * 查询获取分页内容数据
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EUGridResult getContentList(Long categoryId, Integer page, Integer rows) throws Exception {
		EUGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content) {
		TaotaoResult result = contentService.editContent(content);
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(@RequestParam(value="ids")List<Long> ids) {
		TaotaoResult result = contentService.deleteContent(ids);
		return result;
	}


	
	
}
