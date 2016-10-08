package com.aotuspace.aotuerp.web.sperplogin.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;

/**
 * 
 * Title:ISysMMService
 * Description:ϵͳ����Ա����service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:26:40
 *
 */

public interface ISysMMService extends DaoSupport<SpEmployeeBinfo> {

	//���ݵ�¼����������в�ѯ�û�(��¼)--ֻ����ɹ�����Ա�����е�¼
	SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount,String spEppassword) throws Exception;
	
	//��ȡ����Ա��Ϣ��Datatree
	List<SpEmployeeBinfo> findSpEmployeeBinfoTreeData() throws Exception;
	
	//��ѯϵͳ����Ա�б���ҳ��
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception;
}
