package com.aotuspace.aotuerp.web.spwarehouse.service.impl;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;
import com.aotuspace.aotuerp.web.spwarehouse.service.IWarehouseService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class WarehouseServiceImpl extends DaoSupportImpl<SpAotuerpWarehouseInfo> implements IWarehouseService {
	//��ҳ��ѯ
	public PageBean<SpAotuerpWarehouseInfo> findSpAotuerpWarehouseInfoList(int rows, int page) throws Exception {
		//�б�����
		List<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfoList = getSession()
				.createQuery("FROM SpAotuerpWarehouseInfo s").setFirstResult(rows * (page - 1)).setMaxResults(rows)
				.list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpAotuerpWarehouseInfo").uniqueResult();
		return new PageBean<SpAotuerpWarehouseInfo>(count.intValue(), spAotuerpWarehouseInfoList);
	}

	//���ݲֿ��ѯsku��Ʒ�б�
	public PageBean<SpAotuerpProductStocks> findSpProductSkuListByWarehouse(int rows, int page, Integer spWarehouseId)
			throws Exception {
		/*
		//�б�����
		List<SpAotuerpProductStocks> spAotuerpProductStocksList = getSession()
				.createQuery("FROM SpAotuerpProductStocks s WHERE spAotuerpWarehouseInfos.spId=:spWarehouseId)")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).setParameter("spWarehouseId", spWarehouseId).list();
		//�ܼ�¼��
		Long count = (Long) getSession()
				.createQuery("SELECT COUNT(spId) FROM SpAotuerpProductStocks WHERE spAotuerpWarehouseInfos.spId =:spWarehouseId")
				.setParameter("spWarehouseId", spWarehouseId).uniqueResult();
		return new PageBean<SpAotuerpProductStocks>(count.intValue(), spAotuerpProductStocksList);*/
		
		//��������
		//�б�����
		List<SpAotuerpProductStocks> spAotuerpProductStocksList = getSession()
				.createCriteria(SpAotuerpProductStocks.class).createAlias("spAotuerpWarehouseInfos", "spwarehouse")
				.add(Restrictions.eq("spwarehouse.spId", spWarehouseId))
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows).list();
		//�ܼ�¼��
		Long count =(Long) getSession()
		.createCriteria(SpAotuerpProductStocks.class).createAlias("spAotuerpWarehouseInfos", "spwarehouse")
		.add(Restrictions.eq("spwarehouse.spId", spWarehouseId))
		.setProjection(Projections.rowCount()).uniqueResult();
		
		return new PageBean<SpAotuerpProductStocks>(count.intValue(), spAotuerpProductStocksList);
	}
}
