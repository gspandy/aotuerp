package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpAotuerpPurchaseOrdersNumbers
 * Description:�����������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-12 ����10:28:41
 *
 */
public class SpAotuerpPurchaseOrdersNumbers implements Serializable {

	// Fields    

	private Integer spId;
	private String spPurchaseOrderId;
	private SpAotuerpPurchaseOrders spAotuerpPurchaseOrders;

	//private Set<SpAotuerpPurchaseOrders> spAotuerpPurchaseOrderses = new HashSet<SpAotuerpPurchaseOrders>();

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpPurchaseOrderId() {
		return spPurchaseOrderId;
	}

	public void setSpPurchaseOrderId(String spPurchaseOrderId) {
		this.spPurchaseOrderId = spPurchaseOrderId;
	}

	public SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrders() {
		return spAotuerpPurchaseOrders;
	}

	public void setSpAotuerpPurchaseOrders(SpAotuerpPurchaseOrders spAotuerpPurchaseOrders) {
		this.spAotuerpPurchaseOrders = spAotuerpPurchaseOrders;
	}

}