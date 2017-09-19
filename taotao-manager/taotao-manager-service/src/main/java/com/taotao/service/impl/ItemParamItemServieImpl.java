package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;

/**
 * 商品规格产品
 * @author lpz
 *
 */
@Service
public class ItemParamItemServieImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	

	@Override
	public String getItemParamByItemId(Long itemId) {
		// 根据商品ID查询规参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> parmList = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (parmList == null || parmList.size() == 0) {
			return "";
		}
		// 取规格参数信息
		TbItemParamItem item = parmList.get(0);
		String paramData = item.getParamData();
		// 生成HTML
		List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		//sb.append("<div>");
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map map : paramList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			List<Map> params = (List<Map>) map.get("params");
			for (Map map2 : params) {
				sb.append("<tr>\n");
				sb.append("<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("<td>"+map2.get("v")+"</td>\n");
				sb.append("</tr>\n");
			}
		}
		sb.append("</tbody>\n");
		sb.append("</table>");
		//sb.append("</div>");
		return sb.toString();
	}
	
	
	

}
