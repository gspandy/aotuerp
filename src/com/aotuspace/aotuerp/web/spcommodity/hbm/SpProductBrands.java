package com.aotuspace.aotuerp.web.spcommodity.hbm;

/**
 * 
 * Title:SpProductBrands
 * Description:Ʒ����Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-22 ����6:13:25
 *
 */
public class SpProductBrands  {
	private Integer spId;//���
	//private int spCategoryid;//��Ŀ
	private SpProductCategory spProductCategory;
	private String spBrandn;//Ʒ������
	private String spBrandlogo;//Ʒ��logo
	private String spBrandpresent;//Ʒ�ƽ���
	
	public SpProductBrands() {

	}
	
	public SpProductBrands(Integer spId) {
		this.spId=spId;
	}
	


	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public String getSpBrandn() {
		return spBrandn;
	}

	public void setSpBrandn(String spBrandn) {
		this.spBrandn = spBrandn;
	}

	public String getSpBrandlogo() {
		return spBrandlogo;
	}

	public void setSpBrandlogo(String spBrandlogo) {
		this.spBrandlogo = spBrandlogo;
	}

	public String getSpBrandpresent() {
		return spBrandpresent;
	}

	public void setSpBrandpresent(String spBrandpresent) {
		this.spBrandpresent = spBrandpresent;
	}

}
