package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpSupplierBinfo;


/**
 * 
 * Title:ISpSupplierService
 * Description:��Ӧ��service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-10 ����4:58:46
 *
 */

public interface ISpSupplierService extends DaoSupport<SpSupplierBinfo> {
	//��ѯ��Ӧ���б���ҳ��
	PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page) throws Exception;

}
