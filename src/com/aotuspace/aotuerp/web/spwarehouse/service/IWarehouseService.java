package com.aotuspace.aotuerp.web.spwarehouse.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;

/**
 * 
 * Title:ICommodityService
 * Description:���ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-23 ����10:58:19
 *
 */
public interface IWarehouseService extends DaoSupport<SpAotuerpWarehouseInfo> {
	//��ѯ�ֿ��б���ҳ��
	PageBean<SpAotuerpWarehouseInfo> findSpAotuerpWarehouseInfoList(int rows, int page) throws Exception;
	
	//���ݲֿ��ѯsku��Ʒ�б�
	PageBean<SpAotuerpProductStocks> findSpProductSkuListByWarehouse(int rows, int page,Integer spWarehouseId) throws Exception;
}
