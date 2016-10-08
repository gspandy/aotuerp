package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyName;

/**
 * 
 * Title:ICommodityService
 * Description:��Ŀ�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-23 ����10:58:19
 *
 */
public interface ICommodityPropertiesService extends DaoSupport<SpProductPropertyName> {
	//������Ŀ��ѯ����
	List<SpProductPropertyName> findPropertiesByCategoryId(int thirdCategoryId)throws Exception;
	
}
