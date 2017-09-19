package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类的实现类
 * @author lpz
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getItemCatList(Long parentId){
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		// 根据parentid查询
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> catList = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		// 转换为treeNode
		for (TbItemCat cat: catList) {
			EUTreeNode node = new EUTreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		
//		List mapList = new ArrayList();
//		//查询数据库
//		for (TbItemCat tbItemCat : catList) {
//			Map node = new HashMap<>();
//			node.put("id", tbItemCat.getId());
//			node.put("text", tbItemCat.getName());
//			//如果是父节点的话就设置成关闭状态，如果是叶子节点就是open状态
//			node.put("state", tbItemCat.getIsParent()?"closed":"open");
//			mapList.add(node);
//		}
		
		return resultList;
	}
	
}
