package com.aotuspace.aotuerp.web.sptreasure.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.EasyTreeData;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureTask;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureTaskMode;
import com.aotuspace.aotuerp.web.util.TreeNodeUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:CommodityAction
 * Description:��������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����3:22:22
 *
 */
@Controller
@Scope("prototype")
public class TreasureTaskAction extends BaseAction<SpAotuerpTreasureTask> {
	
	private SpAotuerpTreasureTask spAotuerpTreasureTask;
	
	//��������ҳ��
	public String index() throws Exception {
		//��������
		ActionContext.getContext().put("twoNav", twoNav);
		return "index";
	}
	
	//������������ܵ����б�
	public String nav()throws Exception{
		//������������ܵ����б�
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(twoNav);
		
		List<EasyTreeData> easyTreeDatas=TreeNodeUtil.getTreeNode(spEmployeePrivileges,1);
		objectMapper.setSerializationInclusion(Include.NON_NULL);  
		resp.getWriter().write(objectMapper.writeValueAsString(easyTreeDatas));
		
		return NONE;
	}
	//��������ҳ��
	public String release()throws Exception{
		return "release";
	}
	
	//���뱦��
	public String importTreasureData() throws Exception{
		//��ѯ�Ѿ����ͨ���ı���
		PageBean<SpAotuerpTreasureInfo> spAotuerpTreasureInfoList=iSpAotuerpTreasureService.findSpAotuerpTreasureInfoListByTreasureStatus(rows, page, 2);//���ͨ��
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpTreasureInfo.getSpId());//�������
			rowMap.put("sp_idShow", spAotuerpTreasureInfo.getSpId());//�������
			rowMap.put("sp_Treasuretitle", spAotuerpTreasureInfo.getSpTreasuretitle());//��������
			rowMap.put("sp_TreasureStatus", spAotuerpTreasureInfo.getSpAotuerpTreasureStatus().getSpTreasurestatus());//����״̬
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpTreasureInfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//�������������
	public String releaseForm() throws Exception {
		//��ȡ������Ϣ
		SpAotuerpTreasureInfo spAotuerpTreasureInfo=iSpAotuerpTreasureService.getById(model.getSpAotuerpTreasureInfo().getSpId());
		ActionContext.getContext().put("treasureInfo", spAotuerpTreasureInfo);
		//��ȡ��������ģʽ��Ϣ
		List<SpAotuerpTreasureTaskMode> spAotuerpTreasureTaskModes=iSpAotuerpTreasureTaskModeService.findAll();
		ActionContext.getContext().put("treasureTaskModeList", spAotuerpTreasureTaskModes);
		return "releaseForm";
	}
	
	//������������
	public String releaseSubmit()throws Exception{
		iSpAotuerpTreasureTaskService.save(spAotuerpTreasureTask);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	public SpAotuerpTreasureTask getSpAotuerpTreasureTask() {
		return spAotuerpTreasureTask;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureTask(SpAotuerpTreasureTask spAotuerpTreasureTask) {
		this.spAotuerpTreasureTask = spAotuerpTreasureTask;
	}	
}
