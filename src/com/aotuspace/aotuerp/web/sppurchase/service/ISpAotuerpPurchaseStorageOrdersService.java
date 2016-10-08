package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;


/**
 * 
 * Title:ISpSupplierService
 * Description:������service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-10 ����4:58:46
 *
 */

public interface ISpAotuerpPurchaseStorageOrdersService extends DaoSupport<SpAotuerpPurchaseList> {
	
	//���ݽ������Ų�ѯ������
	SpAotuerpPurchaseList findSpAotuerpPurchaseListByPurchaseId(String PurchaseId)throws Exception;
	
}
