package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBrands;

/**
 * 
 * Title:ICommodityService
 * Description:Ʒ�ƽӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-23 ����10:58:19
 *
 */
public interface ICommodityBrandsService extends DaoSupport<SpProductBrands> {
	
	//������ĿId��ѯƷ��
	List<SpProductBrands> findBrandsByCategoryId(int secondCategoryId)throws Exception;
}
