package com.aotuspace.aotuerp.web.sppurchase.hbm;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:SpAotuerpPurchaseOrderPd
 * Description:����������Ʒ��Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-9 ����11:43:52
 *
 */

public class SpAotuerpPurchaseOrderPd {

	private Integer spId;//���
	private SpProductSku spProductSku;//sku��Ʒ��Ϣ
	private Integer spQuantity;//����
	private long spUnitprice;//����
	private long spTotalprice;//�ܼ�

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductSku getSpProductSku() {
		return spProductSku;
	}

	public void setSpProductSku(SpProductSku spProductSku) {
		this.spProductSku = spProductSku;
	}

	public Integer getSpQuantity() {
		return spQuantity;
	}

	public void setSpQuantity(Integer spQuantity) {
		this.spQuantity = spQuantity;
	}

	public long getSpUnitprice() {
		return spUnitprice;
	}

	public void setSpUnitprice(long spUnitprice) {
		this.spUnitprice = spUnitprice;
	}

	public long getSpTotalprice() {
		return spTotalprice;
	}

	public void setSpTotalprice(long spTotalprice) {
		this.spTotalprice = spTotalprice;
	}

}