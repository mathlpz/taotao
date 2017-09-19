package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	EUGridResult getContentList(long catId, Integer page, Integer rows) throws Exception;

	TaotaoResult insertContent(TbContent content);

	TaotaoResult editContent(TbContent content);

	TaotaoResult deleteContent(List<Long> ids);

}
