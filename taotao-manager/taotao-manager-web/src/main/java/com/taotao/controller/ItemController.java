package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem geItemById(@PathVariable Long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	/**
	 * 获取商品分页
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUGridResult getItemList(Integer page, Integer rows){
		EUGridResult itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception{
		TaotaoResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}
	
	
}
