package com.taotao.service;

import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	
	TaotaoResult getItemParamByCid(long cid);
	
	TaotaoResult insertItemParam(TbItemParam itemParam);
	
	EUGridResult getItemParamList(Integer page, Integer rows);
	
}
