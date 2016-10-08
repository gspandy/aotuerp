package com.aotuspace.aotuerp.web.view.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.model.EasyTreeData;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;
import com.aotuspace.aotuerp.web.util.TreeNodeUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * Title:AotuerpAction
 * Description:aotuerpAction����¼ҳ����ҳ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����4:34:29
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "serial","unused" })
public class AotuerpAction extends ActionSupport implements ServletResponseAware{
	
	// ========��ȡjackson����==========
	private ObjectMapper objectMapper=new ObjectMapper();
	
	// ========��ȡHttpServletResponse����==========
	private HttpServletResponse resp;
	
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp=arg0;
		
	}
	
	//��¼ҳ��
	public String login(){
		return "aotuerp_login";
	}
	
	//��ҳ
	public String admin() {
		return "aotuerp_admin";
	}
	
	//����tree����
	public String nav() throws Exception {
		//��ȡ��ǰϵͳ����Ա
		SpEmployeeBinfo currentUser  = (SpEmployeeBinfo) ActionContext
				.getContext().getSession().get("spEmployeeBinfo");
		//��װjson
		//��ȡ�����湦��Ȩ��
		List<SpEmployeePrivilege> erpPrivilegeList= (List<SpEmployeePrivilege>) ActionContext.getContext().getApplication().get("erpPrivilegeList");
		
		List<EasyTreeData> easyTreeDatas=TreeNodeUtil.getTreeNode(erpPrivilegeList,2);
		objectMapper.setSerializationInclusion(Include.NON_NULL);  
		
		resp.getWriter().write(objectMapper.writeValueAsString(easyTreeDatas));
		return NONE;
	}
	
	
}
