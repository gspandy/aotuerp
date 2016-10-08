package com.aotuspace.aotuerp.web.sperplogin.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:ISysPMService
 * Description:ϵͳȨ�޹���service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:27:00
 *
 */
public interface ISysPMService extends DaoSupport<SpEmployeePrivilege>	{
	/**
	 * ��ѯ���н����涥��Ȩ��
	 * @return
	 */
	List<SpEmployeePrivilege> findTopERPPrivList();
	
	//���ݸ��ڵ��ѯȨ��
	List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception;
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 *//*
	Collection<String> getAllPrivilegeUrls();
*/
}
