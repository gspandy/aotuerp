package com.aotuspace.aotuerp.web.sptreasure.hbm;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:SpAotuerpTreasureProductSku
 * Description:������Ʒsku
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-18 ����6:21:47
 *
 */

public class SpAotuerpTreasureProductSku {

	// Fields    

	private Integer spId;
	private SpProductSku spProductSku;//sku��Ʒ
	private SpAotuerpTreasureInfo spAotuerpTreasureInfo;//����
	private long spSalesprice;//���ۼ۸�
	private Integer spSalesquantity;//�������
	private Integer spSalesorderquantity;//��������
	private long spTotalprice;//�ܼ۸�

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductSku getSpProductSku() {
		return this.spProductSku;
	}

	public void setSpProductSku(SpProductSku spProductSku) {
		this.spProductSku = spProductSku;
	}

	public SpAotuerpTreasureInfo getSpAotuerpTreasureInfo() {
		return this.spAotuerpTreasureInfo;
	}

	public void setSpAotuerpTreasureInfo(SpAotuerpTreasureInfo spAotuerpTreasureInfo) {
		this.spAotuerpTreasureInfo = spAotuerpTreasureInfo;
	}

	public long getSpSalesprice() {
		return spSalesprice;
	}

	public void setSpSalesprice(long spSalesprice) {
		this.spSalesprice = spSalesprice;
	}

	public Integer getSpSalesquantity() {
		return spSalesquantity;
	}

	public void setSpSalesquantity(Integer spSalesquantity) {
		this.spSalesquantity = spSalesquantity;
	}

	public Integer getSpSalesorderquantity() {
		return spSalesorderquantity;
	}

	public void setSpSalesorderquantity(Integer spSalesorderquantity) {
		this.spSalesorderquantity = spSalesorderquantity;
	}

	public long getSpTotalprice() {
		return spTotalprice;
	}

	public void setSpTotalprice(long spTotalprice) {
		this.spTotalprice = spTotalprice;
	}

}