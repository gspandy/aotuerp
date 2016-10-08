package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;
import java.util.Set;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:ICommodityService
 * Description:��Ʒ�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-23 ����10:58:19
 *
 */

public interface ICommodityService extends DaoSupport<SpProductBinfo> {
	//��ѯsku��Ʒ�б���ҳ��
	PageBean<SpProductSku> findSpProductSkuList(int rows, int page) throws Exception;

	//��ѯ��Ʒ�б���ҳ��
	PageBean<SpProductBinfo> findSpProductBinfoList(int rows, int page) throws Exception;
	
	//ɾ��sku��Ʒ
	Set<Integer> deleteSpProductSku(Integer[] commoditySKUIds) throws Exception;
	
	//����Ids��ѯsku��Ʒ
	List<SpProductSku> getSpProductSkuByIds(Integer[] ids)throws Exception;
}
