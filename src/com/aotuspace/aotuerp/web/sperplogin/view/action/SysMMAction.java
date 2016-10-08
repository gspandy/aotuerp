package com.aotuspace.aotuerp.web.sperplogin.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeDepart;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysMMAction
 * Description:ϵͳ����Ա����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class SysMMAction extends BaseAction<SpEmployeeBinfo> {
	//��¼����
	public String login() throws Exception {
		//�����ݿ��������û�������
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findByEpAccountAndEpPassword(model.getSpEpaccount(),
				model.getSpEppassword());
		if (!spEmployeeBinfo.getSpEmployeeDepart().getSpEpdepartn().equals("�ɹ���")) {//��¼ʧ��
			jsonResult.setCode(100001);//code
			jsonResult.setMsg("����ʧ��");//msg
			jsonResult.setData(null);//data
		}else{
			//��¼�ɹ�
			//����Session
			ActionContext.getContext().getSession().put("spEmployeeBinfo", spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData(null);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//ע��
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("spEmployeeBInfo");
		return NONE;
	}
	
	//Ա����Ϣdata
	public String listData() throws Exception {
		PageBean<SpEmployeeBinfo> spEmployeeBinfoList=iSysMMService.findSpEmployeeBinfoList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId());
			rowMap.put("sp_EpId", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());
			rowMap.put("sp_Eprealname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());
			if (spEmployeeBinfo.getSpEmployeeDepart() != null) {
				rowMap.put("sp_EpDepartId", spEmployeeBinfo.getSpEmployeeDepart().getSpId());
				rowMap.put("sp_EpDepart", spEmployeeBinfo.getSpEmployeeDepart().getSpEpdepartn());
			}
			rowMap.put("sp_Epsex",spEmployeeBinfo.getSpEmployeeDinfo().getSpEpsex());
			
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spEmployeeBinfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	//getter��setter
}
