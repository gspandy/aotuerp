package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpAotuerpTreasureImg
 * Description:ͼƬ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-11-18 ����6:20:29
 *
 */

public class SpAotuerpTreasureImg {
	private Integer spId;
	private String spImg;
	private Integer spIsprimary;//�Ƿ���ͼ
	private Integer spImgorder;
	private Long spImgsize;//ͼƬ��С
	private Integer spImgwidth;//ͼƬ���
	private Integer spImgheight;//ͼƬ�߶�
	private SpAotuerpTreasureImg orginalImg;//ԭͼ
	private Set<SpAotuerpTreasureImg> thumbnails = new HashSet<SpAotuerpTreasureImg>();

	private Set<SpAotuerpTreasureInfo> spAotuerpTreasureInfos = new HashSet<SpAotuerpTreasureInfo>();

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpImg() {
		return spImg;
	}

	public void setSpImg(String spImg) {
		this.spImg = spImg;
	}

	public Integer getSpIsprimary() {
		return spIsprimary;
	}

	public void setSpIsprimary(Integer spIsprimary) {
		this.spIsprimary = spIsprimary;
	}

	public Integer getSpImgorder() {
		return spImgorder;
	}

	public void setSpImgorder(Integer spImgorder) {
		this.spImgorder = spImgorder;
	}

	public Long getSpImgsize() {
		return spImgsize;
	}

	public void setSpImgsize(Long spImgsize) {
		this.spImgsize = spImgsize;
	}

	public Integer getSpImgheight() {
		return spImgheight;
	}

	public void setSpImgheight(Integer spImgheight) {
		this.spImgheight = spImgheight;
	}

	public Integer getSpImgwidth() {
		return spImgwidth;
	}

	public void setSpImgwidth(Integer spImgwidth) {
		this.spImgwidth = spImgwidth;
	}

	public SpAotuerpTreasureImg getOrginalImg() {
		return orginalImg;
	}

	public void setOrginalImg(SpAotuerpTreasureImg orginalImg) {
		this.orginalImg = orginalImg;
	}

	public Set<SpAotuerpTreasureImg> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Set<SpAotuerpTreasureImg> thumbnails) {
		this.thumbnails = thumbnails;
	}

	public Set<SpAotuerpTreasureInfo> getSpAotuerpTreasureInfos() {
		return spAotuerpTreasureInfos;
	}

	public void setSpAotuerpTreasureInfos(Set<SpAotuerpTreasureInfo> spAotuerpTreasureInfos) {
		this.spAotuerpTreasureInfos = spAotuerpTreasureInfos;
	}

}