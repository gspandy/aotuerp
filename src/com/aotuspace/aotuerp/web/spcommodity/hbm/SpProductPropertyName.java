package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpProductPropertyName
 * Description:��������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-22 ����6:18:20
 *
 */
public class SpProductPropertyName {
	private Integer spId;//���
	//private Integer spParentpvalue;//����ֵid
	private SpProductPropertyValue spProductPropertyValue;//����ֵ
	private String spPropertyname;//������
	private String spAlias;//����
	//private Integer spCategoryid;//������ĿID
	private SpProductCategory spProductCategory;
	private Integer spIscolorpro;//�Ƿ���ɫ����
	private Integer spIsenumpro;//�Ƿ�ö������
	private Integer spIsimportpro;//�Ƿ���������
	private Integer spIskeypro;//�Ƿ�ؼ�����
	private Integer spIssearchpro;//�Ƿ���������
	private Integer spIsrequirepro;//�Ƿ��������
	private Integer spIsmultiselectpro;//�Ƿ��ѡ����
	private Integer spIssalepro;//�Ƿ���������
	private  Integer spSort;//����
	//private Integer spParentid;//������
	private SpProductPropertyName spProductPropertyName;//������
	private Set<SpProductPropertyValue> spProductPropertyValues = new HashSet<SpProductPropertyValue>();//����ֵ
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//������
	private Set<SpProductBproperties> spProductBpropertieses = new HashSet<SpProductBproperties>();//��������
	
	public SpProductPropertyName(){
		
	}
	
	public SpProductPropertyName(Integer spId) {
		this.spId=spId;
	}


	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductPropertyValue getSpProductPropertyValue() {
		return spProductPropertyValue;
	}

	public void setSpProductPropertyValue(SpProductPropertyValue spProductPropertyValue) {
		this.spProductPropertyValue = spProductPropertyValue;
	}

	public String getSpPropertyname() {
		return spPropertyname;
	}

	public void setSpPropertyname(String spPropertyname) {
		this.spPropertyname = spPropertyname;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public Integer getSpIssalepro() {
		return spIssalepro;
	}

	public void setSpIssalepro(Integer spIssalepro) {
		this.spIssalepro = spIssalepro;
	}

	public SpProductPropertyName getSpProductPropertyName() {
		return spProductPropertyName;
	}

	public void setSpProductPropertyName(SpProductPropertyName spProductPropertyName) {
		this.spProductPropertyName = spProductPropertyName;
	}

	public Set<SpProductPropertyValue> getSpProductPropertyValues() {
		return spProductPropertyValues;
	}

	public void setSpProductPropertyValues(Set<SpProductPropertyValue> spProductPropertyValues) {
		this.spProductPropertyValues = spProductPropertyValues;
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

	public Integer getSpIscolorpro() {
		return spIscolorpro;
	}

	public void setSpIscolorpro(Integer spIscolorpro) {
		this.spIscolorpro = spIscolorpro;
	}

	public Integer getSpIsenumpro() {
		return spIsenumpro;
	}

	public void setSpIsenumpro(Integer spIsenumpro) {
		this.spIsenumpro = spIsenumpro;
	}

	public Integer getSpIsimportpro() {
		return spIsimportpro;
	}

	public void setSpIsimportpro(Integer spIsimportpro) {
		this.spIsimportpro = spIsimportpro;
	}

	public Integer getSpIskeypro() {
		return spIskeypro;
	}

	public void setSpIskeypro(Integer spIskeypro) {
		this.spIskeypro = spIskeypro;
	}

	public Integer getSpIssearchpro() {
		return spIssearchpro;
	}

	public void setSpIssearchpro(Integer spIssearchpro) {
		this.spIssearchpro = spIssearchpro;
	}

	public Integer getSpIsrequirepro() {
		return spIsrequirepro;
	}

	public void setSpIsrequirepro(Integer spIsrequirepro) {
		this.spIsrequirepro = spIsrequirepro;
	}

	public Integer getSpIsmultiselectpro() {
		return spIsmultiselectpro;
	}

	public void setSpIsmultiselectpro(Integer spIsmultiselectpro) {
		this.spIsmultiselectpro = spIsmultiselectpro;
	}

	public Integer getSpSort() {
		return spSort;
	}

	public void setSpSort(Integer spSort) {
		this.spSort = spSort;
	}

	public String getSpAlias() {
		return spAlias;
	}

	public void setSpAlias(String spAlias) {
		this.spAlias = spAlias;
	}

}
