package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:SpProductBinfo
 * Description:��Ʒ������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-22 ����6:23:17
 *
 */
public class SpProductBinfo  {
	private Integer spId;
	private String spPdspu;//��Ʒ����
	private String spPdtitle;//��Ʒ����
	private String spPdfeature;//��Ʒ��������
	private Date spPdcredate;//��������
	private SpProductBrands spProductBrands;//Ʒ��
	private Set<SpProductBproperties> spProductBproperties = new HashSet<SpProductBproperties>();
	private Set<SpProductSku> spProductSkus = new HashSet<SpProductSku>();

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpPdspu() {
		return spPdspu;
	}

	public void setSpPdspu(String spPdspu) {
		this.spPdspu = spPdspu;
	}

	public SpProductBrands getSpProductBrands() {
		return spProductBrands;
	}

	public void setSpProductBrands(SpProductBrands spProductBrands) {
		this.spProductBrands = spProductBrands;
	}

	public String getSpPdtitle() {
		return spPdtitle;
	}

	public void setSpPdtitle(String spPdtitle) {
		this.spPdtitle = spPdtitle;
	}

	public String getSpPdfeature() {
		return spPdfeature;
	}

	public void setSpPdfeature(String spPdfeature) {
		this.spPdfeature = spPdfeature;
	}

	public Date getSpPdcredate() {
		return spPdcredate;
	}

	public void setSpPdcredate(Date spPdcredate) {
		this.spPdcredate = spPdcredate;
	}

	public Set<SpProductBproperties> getSpProductBproperties() {
		return spProductBproperties;
	}

	public void setSpProductBproperties(Set<SpProductBproperties> spProductBproperties) {
		this.spProductBproperties = spProductBproperties;
	}

	public Set<SpProductSku> getSpProductSkus() {
		return spProductSkus;
	}

	public void setSpProductSkus(Set<SpProductSku> spProductSkus) {
		this.spProductSkus = spProductSkus;
	}

}
