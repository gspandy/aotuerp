package com.aotuspace.aotuerp.web.spwarehouse.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:CommodityAction
 * Description:��Ʒ����ģ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����3:22:22
 *
 */
@Controller
@Scope("prototype")
public class WarehouseAction extends BaseAction<SpAotuerpWarehouseInfo> {

	public Integer empSpId;
	public Integer empSpEpId;
	//�ֿ�Ids
	public Integer[] warehouseIds;

	//��Ʒ��Ϣҳ��
	public String list() throws Exception {
		return "list";
	}

	//��Ʒ��ϢlistData����
	public String listData() throws Exception {
		PageBean<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfoList = iWarehouseService.findSpAotuerpWarehouseInfoList(
				rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpWarehouseInfo spAotuerpWarehouseInfo : spAotuerpWarehouseInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpWarehouseInfo.getSpId());//�ֿ���
			rowMap.put("sp_idShow", spAotuerpWarehouseInfo.getSpId());//�ֿ���
			rowMap.put("sp_warehousename", spAotuerpWarehouseInfo.getSpWarehousename());//�ֿ�����
			String sp_empwarehouse="";
			for (SpEmployeeBinfo spEmployeeBinfo : spAotuerpWarehouseInfo.getSpEmployeeBinfos()) {
				sp_empwarehouse=spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname();
			}
			rowMap.put("sp_empwarehouse",sp_empwarehouse);//�ֿ����Ա����
			rowMap.put("sp_pinyin", spAotuerpWarehouseInfo.getSpPinyin());//�ֿ�ƴ����
			rowMap.put("sp_remark", spAotuerpWarehouseInfo.getSpRemark());//��ע
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpWarehouseInfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//�ֿ����Ա
	public String empWarehouseTreeData() throws Exception {
		boolean flag = true;
		List<SpEmployeeBinfo> spEmployeeBinfoList = iSysMMService.findSpEmployeeBinfoTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfoList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId() + "-"
					+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());
			rowMap.put("text", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());
			if (flag) {
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ��ֿ����Ա");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//��Ӳֿ�
	public String add() throws Exception {
		SpEmployeeBinfoKey spEmployeeBinfoKey = new SpEmployeeBinfoKey();
		spEmployeeBinfoKey.setSpId(empSpId);
		spEmployeeBinfoKey.setSpEpid(empSpEpId);
		//��ѯԱ��
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(spEmployeeBinfoKey);
		Set<SpEmployeeBinfo> spEmployeeBinfos = new HashSet<SpEmployeeBinfo>();
		spEmployeeBinfos.add(spEmployeeBinfo);
		model.setSpEmployeeBinfos(spEmployeeBinfos);
		//����ֿ�
		iWarehouseService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	//���²ֿ�
	public String edit()throws Exception{
		SpAotuerpWarehouseInfo spAotuerpWarehouseInfo=iWarehouseService.getById(model.getSpId());
		// �������Ϊ�գ���
		if (spAotuerpWarehouseInfo != null) {
			System.out.println(spAotuerpWarehouseInfo.getSpEmployeeBinfos());//jacksonע����Hibernate4Module����һЩ���Բ������л�����������Ҫ��ӡһ�³���
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuerpWarehouseInfo) + "]");
		} else { // ����
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		System.out.println(objectMapper.writeValueAsString(jsonResult));
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	public String editSubmit() throws Exception{
		//��ȡ�ֿ���Ϣ
		SpAotuerpWarehouseInfo spAotuerpWarehouseInfo=iWarehouseService.getById(model.getSpId());
		spAotuerpWarehouseInfo.setSpWarehousename(model.getSpWarehousename());//�ֿ�����
		spAotuerpWarehouseInfo.setSpPinyin(model.getSpPinyin());//ƴ����
		spAotuerpWarehouseInfo.setSpAddress(model.getSpAddress());//�ֿ��ַ
		spAotuerpWarehouseInfo.setSpRemark(model.getSpRemark());//��ע
		SpEmployeeBinfoKey spEmployeeBinfoKey = new SpEmployeeBinfoKey();
		spEmployeeBinfoKey.setSpId(empSpId);
		spEmployeeBinfoKey.setSpEpid(empSpEpId);
		//��ѯԱ��
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(spEmployeeBinfoKey);
		Set<SpEmployeeBinfo> spEmployeeBinfos = new HashSet<SpEmployeeBinfo>();
		spEmployeeBinfos.add(spEmployeeBinfo);
		
		spAotuerpWarehouseInfo.setSpEmployeeBinfos(spEmployeeBinfos);//�ֿ����Ա
		
		iWarehouseService.update(spAotuerpWarehouseInfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//ɾ���ֿ�
	public String delete() throws Exception{
		iWarehouseService.delete(warehouseIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
		
	}
	
	//�ֿ���Ʒ��Ϣ�б�
	public String pdList()throws Exception{
		return "pdList";
	}
	
	//�ֿ����tree
	public String warehouseTree() throws Exception {
		//��ѯ�ֿ�����
		List<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfoList = iWarehouseService.findAll();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpWarehouseInfo spAotuerpWarehouseInfo : spAotuerpWarehouseInfoList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuerpWarehouseInfo.getSpId());
			rowMap.put("text", spAotuerpWarehouseInfo.getSpWarehousename()+"(���:"+spAotuerpWarehouseInfo.getSpId()+")");
			listMaps.add(rowMap);
		}
		//easyui
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//�����Ʒ����
	public String warehousePdListData()throws Exception{
		PageBean<SpAotuerpProductStocks> spAotuerpProductStocksList;
		if(model.getSpId()!=null && model.getSpId()!=0){
			spAotuerpProductStocksList = iWarehouseService.findSpProductSkuListByWarehouse(rows, page, model.getSpId());
		}else{
			//��ѯsku��Ʒ�б�����
			spAotuerpProductStocksList=new PageBean<SpAotuerpProductStocks>();
		}
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpProductStocks spAotuerpProductStocks : spAotuerpProductStocksList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpProductStocks.getSpProductSku().getSpSkuid());//��Ʒskuid
			rowMap.put("sp_Pdspu", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpPdspu());//��Ʒ����
			rowMap.put("sp_Pdproname", spAotuerpProductStocks.getSpProductSku().getSpSkupropertiesname());//����ͺ�
			rowMap.put("sp_Pdbrand", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpProductBrands().getSpBrandn());//Ʒ��
			rowMap.put("sp_PdCategory", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpProductBrands().getSpProductCategory().getSpCategoryn());//��Ŀ��
			rowMap.put("sp_Quantity", spAotuerpProductStocks.getSpQuantity());//����
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpProductStocksList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	

	public Integer getEmpSpId() {
		return empSpId;
	}

	public void setEmpSpId(Integer empSpId) {
		this.empSpId = empSpId;
	}

	public Integer getEmpSpEpId() {
		return empSpEpId;
	}

	public void setEmpSpEpId(Integer empSpEpId) {
		this.empSpEpId = empSpEpId;
	}

	public Integer[] getWarehouseIds() {
		return warehouseIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setWarehouseIds(Integer[] warehouseIds) {
		this.warehouseIds = warehouseIds;
	}

}
