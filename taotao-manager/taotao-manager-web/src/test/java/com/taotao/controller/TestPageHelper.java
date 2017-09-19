package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;


/**
 * PageHelper分页测试
 * @author lpz
 *
 */
public class TestPageHelper {

	@Test
	public void testPageHelper(){
		// 创建一个spring
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// 从spring容器中获取mapper的代理对象
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		// 执行查询，并分页
		TbItemExample example = new TbItemExample();
		// 分液处理
		PageHelper.startPage(1, 10);
		List<TbItem> list = mapper.selectByExample(example);
		// 取商品列表
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		PageInfo<TbItem> page = new PageInfo<>(list);
		System.out.println("商品总条数：" + page.getTotal());
		System.out.println("商品总页数：" + page.getPages());
		
	}
	
}
