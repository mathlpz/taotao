package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	

	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	@Override
	public EUGridResult getContentList(long catId, Integer page, Integer rows) throws Exception {
		//根据category_id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EUGridResult result = new EUGridResult(pageInfo.getTotal(), list);
		return result;
	}
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult editContent(TbContent content) {
		//补全pojo内容
		TbContent cont = contentMapper.selectByPrimaryKey(content.getId());
		cont = content;
		cont.setUpdated(new Date());
		contentMapper.updateByPrimaryKey(cont);
		
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + cont.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult deleteContent(List<Long> ids) {
//		TbContentExample example = new TbContentExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andCategoryIdIn(ids);
//		contentMapper.deleteByExample(example);
		for (Long id:ids) {
			contentMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}


	
	
}
