package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrders;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersNumbers;


/**
 * 
 * Title:ISpSupplierService
 * Description:��������service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-10 ����4:58:46
 *
 */

public interface ISpAotuerpPurchaseOrdersService extends DaoSupport<SpAotuerpPurchaseOrdersNumbers> {
	//��ѯ����id���ֵ
	Integer findByMaxPurchaseOrdersNum()throws Exception;
	//��ѯδ��ɵĽ��������б���ҳ��
	PageBean<SpAotuerpPurchaseOrdersNumbers> findSpAotuerpPurchaseOrdersNumbersListNotFinish(int rows, int page) throws Exception;
	//��ѯ�������б���ҳ��
	PageBean<SpAotuerpPurchaseList> findSpAotuerpPurchaseList(int rows, int page) throws Exception;
	
	
	//����id��ѯ�����������Ƕ��������Ų�ѯ��
	SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrdersById (Integer id)throws Exception;
	
	
}
