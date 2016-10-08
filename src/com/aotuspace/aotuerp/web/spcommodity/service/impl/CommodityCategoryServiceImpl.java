package com.aotuspace.aotuerp.web.spcommodity.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityCategoryService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class CommodityCategoryServiceImpl extends DaoSupportImpl<SpProductCategory> implements ICommodityCategoryService {
	//��ѯ������Ŀ
	public List<SpProductCategory> findCategoryTopList() throws Exception {
		return getSession().createQuery("FROM SpProductCategory WHERE spProductCategory.spId IS NULL").list();
	}
	
	//���ݶ�����Ŀ��ѯ����Ŀ
	public List<SpProductCategory> findCategoryByTopList(int topCategoryId) throws Exception {
		return getSession().createQuery("FROM SpProductCategory WHERE spProductCategory.spId =:topCategoryId")
				.setParameter("topCategoryId", topCategoryId)
				.list();
	}
}
