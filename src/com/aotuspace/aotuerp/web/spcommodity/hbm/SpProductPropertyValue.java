package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;


/**
 * 
 * Title:SpProductPropertyValue
 * Description:����ֵ��Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-22 ����6:21:02
 *
 */
public class SpProductPropertyValue  {
	private Integer spId;//���
	private String spPropertyvalue;//����ֵ
	private Integer spPropertynameid;//������id
	//private Integer spCategoryid;//������ĿID
	private SpProductCategory spProductCategory;//��Ŀ
	private SpProductPropertyName spProductPropertyName;//������
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//������
	private Set<SpProductBproperties> spProductBpropertieses = new HashSet<SpProductBproperties>();//����ֵ

	public SpProductPropertyValue(){
		
	}
	
	public SpProductPropertyValue(Integer spId) {
		this.spId=spId;
	}
	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpPropertyvalue() {
		return spPropertyvalue;
	}

	public void setSpPropertyvalue(String spPropertyvalue) {
		this.spPropertyvalue = spPropertyvalue;
	}

	public Integer getSpPropertynameid() {
		return spPropertynameid;
	}

	public void setSpPropertynameid(Integer spPropertynameid) {
		this.spPropertynameid = spPropertynameid;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public SpProductPropertyName getSpProductPropertyName() {
		return spProductPropertyName;
	}

	public void setSpProductPropertyName(SpProductPropertyName spProductPropertyName) {
		this.spProductPropertyName = spProductPropertyName;
	}

	public Set<SpProductPropertyName> getSpProductPropertyNames() {
		return spProductPropertyNames;
	}

	public void setSpProductPropertyNames(Set<SpProductPropertyName> spProductPropertyNames) {
		this.spProductPropertyNames = spProductPropertyNames;
	}

	public Set<SpProductBproperties> getSpProductBpropertieses() {
		return spProductBpropertieses;
	}

	public void setSpProductBpropertieses(Set<SpProductBproperties> spProductBpropertieses) {
		this.spProductBpropertieses = spProductBpropertieses;
	}

}
