package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.HashSet;
import java.util.Set;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;

/**
 * 
 * Title:SpAotuerpTreasureInfo
 * Description:������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-18 ����6:13:53
 *
 */

public class SpAotuerpTreasureInfo {

	// Fields    

	private Integer spId;
	private SpAotuerpTreasureStatus spAotuerpTreasureStatus;//״̬
	private String spTreasuretitle;//����
	private String spTreasuresellingpoints;//����
	private String spTreasuredescription;//���������ı�
	private Set<SpAotuerpTreasureProductSku> spAotuerpTreasureProductSkus = new HashSet<SpAotuerpTreasureProductSku>();//����sku
	private Set<SpAotuerpTreasureImg> spAotuerpTreasureImgs = new HashSet<SpAotuerpTreasureImg>();//ͼƬ
	private SpProductBinfo spProductBinfo;//��Ʒ

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpAotuerpTreasureStatus getSpAotuerpTreasureStatus() {
		return this.spAotuerpTreasureStatus;
	}

	public void setSpAotuerpTreasureStatus(SpAotuerpTreasureStatus spAotuerpTreasureStatus) {
		this.spAotuerpTreasureStatus = spAotuerpTreasureStatus;
	}

	public String getSpTreasuretitle() {
		return spTreasuretitle;
	}

	public void setSpTreasuretitle(String spTreasuretitle) {
		this.spTreasuretitle = spTreasuretitle;
	}

	public String getSpTreasuresellingpoints() {
		return spTreasuresellingpoints;
	}

	public void setSpTreasuresellingpoints(String spTreasuresellingpoints) {
		this.spTreasuresellingpoints = spTreasuresellingpoints;
	}

	public String getSpTreasuredescription() {
		return spTreasuredescription;
	}

	public void setSpTreasuredescription(String spTreasuredescription) {
		this.spTreasuredescription = spTreasuredescription;
	}

	public Set<SpAotuerpTreasureProductSku> getSpAotuerpTreasureProductSkus() {
		return spAotuerpTreasureProductSkus;
	}

	public void setSpAotuerpTreasureProductSkus(Set<SpAotuerpTreasureProductSku> spAotuerpTreasureProductSkus) {
		this.spAotuerpTreasureProductSkus = spAotuerpTreasureProductSkus;
	}

	public Set<SpAotuerpTreasureImg> getSpAotuerpTreasureImgs() {
		return spAotuerpTreasureImgs;
	}

	public void setSpAotuerpTreasureImgs(Set<SpAotuerpTreasureImg> spAotuerpTreasureImgs) {
		this.spAotuerpTreasureImgs = spAotuerpTreasureImgs;
	}

	public SpProductBinfo getSpProductBinfo() {
		return spProductBinfo;
	}

	public void setSpProductBinfo(SpProductBinfo spProductBinfo) {
		this.spProductBinfo = spProductBinfo;
	}

}