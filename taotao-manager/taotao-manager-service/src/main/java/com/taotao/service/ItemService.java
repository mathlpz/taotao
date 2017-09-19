package com.taotao.service;

import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EUGridResult getItemList(Integer page, Integer rows);
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
	
}
