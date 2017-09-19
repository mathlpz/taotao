package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author lpz
 * @date	2015年9月2日上午10:47:14
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		
//		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		// 添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 分页查询
	 */
	@Override
	public EUGridResult getItemList(Integer pageNum, Integer pageSize) {
		// 查询商品列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(pageNum, pageSize);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建一个返回值对象
		EUGridResult result = new EUGridResult();
		result.setRows(list);
		// 取记录总数
		PageInfo<TbItem> page = new PageInfo<>(list);
		result.setTotal(page.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		// 把item补全
		Long itemID = IDUtils.genItemId();
		item.setId(itemID);
		item.setStatus((byte) 1);	// 1正常，2下架，3删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入数据库
		itemMapper.insert(item);
		//插入商品描述
		TaotaoResult result = insertItemDesc(itemID, desc);
		if (result.getStatus() != 200) {
			throw new Exception("保存商品描述信息失败，请重试");
		}
		// 添加规格参数
		result = intertItemParamItem(itemID, itemParam);
		if (result.getStatus() != 200) {
			throw new Exception("保存商品规格参数失败，请重试");
		}
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品描述
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	/**
	 * 添加商品规格说明
	 * @param itemId
	 * @param itemParam
	 * @return
	 */
	private TaotaoResult intertItemParamItem(Long itemId, String itemParam){
		TbItemParamItem pitem = new TbItemParamItem();
		pitem.setItemId(itemId);
		pitem.setParamData(itemParam);
		pitem.setCreated(new Date());
		pitem.setUpdated(new Date());
		itemParamItemMapper.insert(pitem);
		return TaotaoResult.ok();
	}
	

}
