package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;
import java.util.Set;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;

/**
 * 
 * Title:ICommodityService
 * Description:��Ŀ�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-23 ����10:58:19
 *
 */
public interface ICommodityCategoryService extends DaoSupport<SpProductCategory> {
	//��ѯ������Ŀ
	List<SpProductCategory> findCategoryTopList()throws Exception;
	//���ݶ�����Ŀ��ѯ����Ŀ
	List<SpProductCategory> findCategoryByTopList(int topCategoryId)throws Exception;
}
