package com.aotuspace.aotuerp.web.spwarehouse.hbm;

import java.util.HashSet;
import java.util.Set;

import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;

/**
 * 
 * Title:SpWarehouseInfo
 * Description:�ֿ���Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-5 ����2:45:26
 *
 */
public class SpAotuerpWarehouseInfo{
	private Integer spId;//���
	private String spWarehousename;//�ֿ���
	private String spRemark;//��ע
	private String spPinyin;//ƴ����
	private String spAddress;//��ַ
	private Set<SpEmployeeBinfo> spEmployeeBinfos = new HashSet<SpEmployeeBinfo>();
	private Set<SpAotuerpProductStocks> spAotuerpProductStockses = new HashSet<SpAotuerpProductStocks>();

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpWarehousename() {
		return spWarehousename;
	}

	public void setSpWarehousename(String spWarehousename) {
		this.spWarehousename = spWarehousename;
	}

	public String getSpRemark() {
		return spRemark;
	}

	public void setSpRemark(String spRemark) {
		this.spRemark = spRemark;
	}

	public String getSpPinyin() {
		return spPinyin;
	}

	public void setSpPinyin(String spPinyin) {
		this.spPinyin = spPinyin;
	}

	public String getSpAddress() {
		return spAddress;
	}

	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}

	public Set<SpEmployeeBinfo> getSpEmployeeBinfos() {
		return spEmployeeBinfos;
	}

	public void setSpEmployeeBinfos(Set<SpEmployeeBinfo> spEmployeeBinfos) {
		this.spEmployeeBinfos = spEmployeeBinfos;
	}

	public Set<SpAotuerpProductStocks> getSpAotuerpProductStockses() {
		return spAotuerpProductStockses;
	}

	public void setSpAotuerpProductStockses(Set<SpAotuerpProductStocks> spAotuerpProductStockses) {
		this.spAotuerpProductStockses = spAotuerpProductStockses;
	}

}
