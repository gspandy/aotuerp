package com.aotuspace.aotuerp.web.sppurchase.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpSupplierBinfo;

/**
 * 
 * Title:CommodityAction
 * Description:��Ӧ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����3:22:22
 *
 */
@Controller
@Scope("prototype")
public class SpSupplierAction extends BaseAction<SpSupplierBinfo> {

	//��Ӧ����Ϣdata
	public String listData() throws Exception {
		PageBean<SpSupplierBinfo> spSupplierBinfoList = iSpSupplierService.findSpSupplierBinfoList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpSupplierBinfo spSupplierBinfo : spSupplierBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierBinfo.getSpSupplierBinfoKey().getSpId());//���id
			rowMap.put("sp_SuId", spSupplierBinfo.getSpSupplierBinfoKey().getSpSuid());//��Ӧ��id
			rowMap.put("sp_SuSup", spSupplierBinfo.getSpSupplierDinfo().getSpSusup());//�̼�����
			rowMap.put("sp_SuCont", spSupplierBinfo.getSpSupplierDinfo().getSpSucont());//�̼���ϵ������
			rowMap.put("sp_SuMobie", spSupplierBinfo.getSpSupplierDinfo().getSpSumobie());//�̼��ֻ�����
			rowMap.put("sp_SuTel", spSupplierBinfo.getSpSupplierDinfo().getSpSutel());//�̼���ϵ�绰
			rowMap.put("sp_SuDistrict", spSupplierBinfo.getSpSupplierDinfo().getSpSudistrict());//�̼����ڵ�
			listMaps.add(rowMap);
		}
		//easyui total ����rows�б�
		pageListMap.put("total", spSupplierBinfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
}
