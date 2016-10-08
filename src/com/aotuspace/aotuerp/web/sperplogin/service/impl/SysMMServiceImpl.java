package com.aotuspace.aotuerp.web.sperplogin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysMMService;

/**
 * 
 * Title:SysMMServiceImpl
 * Description:ϵͳ����Ա����serviceʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:27:49
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysMMServiceImpl extends DaoSupportImpl<SpEmployeeBinfo> implements ISysMMService {
	//��¼
	public SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount, String spEppassword) throws Exception {
		return (SpEmployeeBinfo) getSession()
				.createQuery("FROM SpEmployeeBinfo s WHERE s.spEpaccount=? AND s.spEppassword=? ")
				.setParameter(0, spEpaccount).setParameter(1, spEppassword).uniqueResult();
	}

	//��ȡ����Ա��Ϣ��Datatree
	public List<SpEmployeeBinfo> findSpEmployeeBinfoTreeData() throws Exception {
		return getSession().createQuery("FROM SpEmployeeBinfo").list();
	}
	
	public PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception {
		//�б�����
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s")
				.setFirstResult(rows*(page-1))
				.setMaxResults(rows)
				.list();
		//�ܼ�¼��
		Long count = (Long) getSession().
				createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo")
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}
}
