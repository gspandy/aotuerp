package com.aotuspace.aotuerp.web.sptreasure.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureImg;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;


/**
 * 
 * Title:ISpSupplierService
 * Description:����service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-10 ����4:58:46
 *
 */

public interface ISpAotuerpTreasureService extends DaoSupport<SpAotuerpTreasureInfo> {	
	
	/**
	 * ��ѯ�����б�
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoList(int rows, int page) throws Exception;
	
	/**
	 * ���ݱ���״̬��ѯ�����б�
	 * @param rows
	 * @param page
	 * @param treasureStatus
	 * @return
	 * @throws Exception
	 */
	PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoListByTreasureStatus(int rows, int page,Integer treasureStatus) throws Exception;
	
	/**
	 * ���汦��ͼƬ
	 * @param spAotuerpTreasureImg
	 * @throws Exception
	 */
	void saveSpAotuerpTreasureImg(SpAotuerpTreasureImg spAotuerpTreasureImg)throws Exception;
	
}
