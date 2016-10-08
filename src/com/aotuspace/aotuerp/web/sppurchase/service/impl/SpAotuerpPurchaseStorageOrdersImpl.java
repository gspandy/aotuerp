package com.aotuspace.aotuerp.web.sppurchase.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpAotuerpPurchaseStorageOrdersService;

/**
 * 
 * Title:SpAotuerpPurchaseOrdersNumbersImpl
 * Description:������ʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-12 ����10:34:49
 *
 */
@Service
@Transactional
public class SpAotuerpPurchaseStorageOrdersImpl extends DaoSupportImpl<SpAotuerpPurchaseList> implements ISpAotuerpPurchaseStorageOrdersService {

	public SpAotuerpPurchaseList findSpAotuerpPurchaseListByPurchaseId(String PurchaseId) throws Exception {
		return (SpAotuerpPurchaseList) getSession()
				.createQuery("From SpAotuerpPurchaseList WHERE spPurchaseId=:spPurchaseId")
				.setParameter("spPurchaseId", PurchaseId).uniqueResult();
	}
}
