package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpProductCategory
 * Description:��Ŀ��Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-22 ����6:16:29
 *
 */
public class SpProductCategory  {
	private Integer spId;//���
	private String spCategoryn;//��Ŀ����
	//private String spCategorypid;//����ĿID
	private SpProductCategory spProductCategory;//����Ŀ
	private Set<SpProductCategory> spProductCategories = new HashSet<SpProductCategory>();//����Ŀ
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//������
	private Set<SpProductPropertyValue> spProductPropertyValues = new HashSet<SpProductPropertyValue>();//����ֵ
	private Set<SpProductBrands> spProductBrandses = new HashSet<SpProductBrands>();//Ʒ��

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpCategoryn() {
		return spCategoryn;
	}

	public void setSpCategoryn(String spCategoryn) {
		this.spCategoryn = spCategoryn;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public Set<SpProductCategory> getSpProductCategories() {
		return spProductCategories;
	}

	public void setSpProductCategories(Set<SpProductCategory> spProductCategories) {
		this.spProductCategories = spProductCategories;
	}

	public Set<SpProductPropertyName> getSpProductPropertyNames() {
		return spProductPropertyNames;
	}

	public void setSpProductPropertyNames(Set<SpProductPropertyName> spProductPropertyNames) {
		this.spProductPropertyNames = spProductPropertyNames;
	}

	public Set<SpProductPropertyValue> getSpProductPropertyValues() {
		return spProductPropertyValues;
	}

	public void setSpProductPropertyValues(Set<SpProductPropertyValue> spProductPropertyValues) {
		this.spProductPropertyValues = spProductPropertyValues;
	}

	public Set<SpProductBrands> getSpProductBrandses() {
		return spProductBrandses;
	}

	public void setSpProductBrandses(Set<SpProductBrands> spProductBrandses) {
		this.spProductBrandses = spProductBrandses;
	}

}
