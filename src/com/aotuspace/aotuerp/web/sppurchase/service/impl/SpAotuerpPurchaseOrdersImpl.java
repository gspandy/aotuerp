package com.aotuspace.aotuerp.web.sppurchase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrders;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersNumbers;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpAotuerpPurchaseOrdersService;

/**
 * 
 * Title:SpAotuerpPurchaseOrdersNumbersImpl
 * Description:��������ʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-12 ����10:34:49
 *
 */
@Service
@Transactional
public class SpAotuerpPurchaseOrdersImpl extends DaoSupportImpl<SpAotuerpPurchaseOrdersNumbers> implements ISpAotuerpPurchaseOrdersService {
	
	public Integer findByMaxPurchaseOrdersNum() throws Exception {
		return (Integer) getSession().createQuery("SELECT MAX(spId) FROM SpAotuerpPurchaseOrdersNumbers")
				.uniqueResult();
	}
	
	//��ҳ��ѯ
	public PageBean<SpAotuerpPurchaseOrdersNumbers> findSpAotuerpPurchaseOrdersNumbersListNotFinish(int rows, int page)
			throws Exception {
		//�б�����
		List<SpAotuerpPurchaseOrdersNumbers> spAotuerpPurchaseOrdersNumbersList = getSession()
				.createQuery("FROM SpAotuerpPurchaseOrdersNumbers s WHERE s.spAotuerpPurchaseOrders.spAotuerpPurchaseOrdersStatus.spId = 1").setFirstResult(rows * (page - 1))
				.setMaxResults(rows).list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpAotuerpPurchaseOrdersNumbers s WHERE s.spAotuerpPurchaseOrders.spAotuerpPurchaseOrdersStatus.spId = 1")
				.uniqueResult();
		return new PageBean<SpAotuerpPurchaseOrdersNumbers>(count.intValue(), spAotuerpPurchaseOrdersNumbersList);
	}

	//��ҳ��ѯ
	public PageBean<SpAotuerpPurchaseList> findSpAotuerpPurchaseList(int rows, int page) throws Exception {
		//�б�����
		List<SpAotuerpPurchaseList> spAotuerpPurchaseList = getSession().createQuery("FROM SpAotuerpPurchaseList s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpAotuerpPurchaseList").uniqueResult();
		return new PageBean<SpAotuerpPurchaseList>(count.intValue(), spAotuerpPurchaseList);
	}

	public SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrdersById(Integer id) throws Exception {
		return (SpAotuerpPurchaseOrders) getSession().get(SpAotuerpPurchaseOrders.class, id);
	}

}
