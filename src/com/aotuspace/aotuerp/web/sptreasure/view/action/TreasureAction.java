package com.aotuspace.aotuerp.web.sptreasure.view.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.ImgSpec;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.model.UpLoad;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBproperties;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureImg;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureProductSku;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureStatus;
import com.aotuspace.aotuerp.web.util.sort.ISortSpProductBproperties;
import com.aotuspace.aotuerp.web.util.sort.complarator.SortSpProductBpropertiesImp;
import com.aotuspace.aotuerp.web.util.sort.complarator.SpProductBpropertiesComparator;
import com.aotuspace.aotuerp.web.util.sort.rule.SpProductBpropertiesOrder;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:CommodityAction
 * Description:��������ģ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����3:22:22
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TreasureAction extends BaseAction<SpAotuerpTreasureInfo> {

	private SpAotuerpTreasureInfo spAotuerpTreasureInfo;

	//skuid
	private Integer[] spProductSkuIds;

	//��Ʒid
	private Integer spProductId;

	//����ids
	private Integer[] spAotuerpTreasureIds;

	//�������
	private boolean treasureOrderCheck;

	private UpLoad spTreasureimg_pic;

	//�����ϼܶ���
	public String treasureShelvesOrder() throws Exception {
		return "treasureShelvesOrder";
	}

	//���������ϼܱ�
	public String treasureShelvesOrderForm() throws Exception {
		SpProductBinfo spProductBinfo = iCommodityService.getById(spProductId);
		//��ȡ
		ActionContext.getContext().put(
				"categoryBrands",
				getParentName(spProductBinfo.getSpProductBrands().getSpProductCategory())
						+ spProductBinfo.getSpProductBrands().getSpBrandn());
		List<SpProductBproperties> spProductBpropertiesList = new ArrayList<SpProductBproperties>();
		for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
			if (spProductBproperties.getSpIssku() == 0) {
				spProductBpropertiesList.add(spProductBproperties);
			}
		}
		ActionContext.getContext().put("spProductBpropertiesList", spProductBpropertiesList);
		ActionContext.getContext().put("spProductId", spProductBinfo.getSpId());
		return "treasureShelvesOrderForm";
	}

	//����sku��Ʒ
	public String importPdSkuData() throws Exception {
		List<SpProductSku> spProductSkus = iCommodityService.getSpProductSkuByIds(spProductSkuIds);
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkus) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("spSkuid", spProductSku.getSpSkuid());//skuid
			rowMap.put("spSkuproperties", spProductSku.getSpSkuproperties());//sku��Ϣֵ
			rowMap.put("spSkupropertiesname", spProductSku.getSpSkupropertiesname());//sku��Ϣ��
			rowMap.put("spPdprice", spProductSku.getSpPdprice());//�۸�
			rowMap.put("spPdcount", spProductSku.getSpPdcount());//����

			//����ʼ�������⣩
			//��setת��list
			List<SpProductBproperties> spProductBpropertiesList = new ArrayList(
					spProductSku.getSpProductBpropertieses());
			//�½��������
			List<SpProductBpropertiesOrder> orders = new ArrayList<SpProductBpropertiesOrder>();
			//�����������
			SpProductBpropertiesOrder spProductBpropertiesOrder = new SpProductBpropertiesOrder(
					"spProductPropertyName", true, SpProductBpropertiesOrder._Integer);
			//����������
			orders.add(spProductBpropertiesOrder);
			//����������
			//spProductBpropertiesOrder =new SpProductBpropertiesOrder("spProductPropertyName", true, SpProductBpropertiesOrder._STRING);
			//orders.add(spProductBpropertiesOrder);
			//���ӿڴ������
			ISortSpProductBproperties iSortSpProductBproperties = new SortSpProductBpropertiesImp(orders);
			SpProductBpropertiesComparator comparator = new SpProductBpropertiesComparator(iSortSpProductBproperties);
			Collections.sort(spProductBpropertiesList, comparator);

			if (spProductSku.getSpProductBinfo() != null) {
				//��Ʒȫ������������Ϣ
				for (SpProductBproperties spProductBproperties : spProductSku.getSpProductBinfo()
						.getSpProductBproperties()) {
					System.out.println(spProductBproperties.getSpProductPropertyName());
					System.out.println(spProductBproperties.getSpProductPropertyValue());
				}
			}
			/*
			System.out.println("����ǰ------");
			for (SpProductBproperties spProductBproperties : spProductSku.getSpProductBpropertieses()) {
				System.out.println("sku��Ϣ--->"+spProductBproperties.getSpProductPropertyName().getSpPropertyname());
			}
			
			System.out.println("�����------");
			for (SpProductBproperties spProductBproperties : spProductBpropertiesList) {
				System.out.println("sku��Ϣ--->"+spProductBproperties.getSpProductPropertyName().getSpPropertyname());
			}*/
			//�������

			rowMap.put("spProductBpropertieses", spProductBpropertiesList);//sku������Ϣ

			rowMap.put("spProductBinfo", spProductSku.getSpProductBinfo());//��Ʒ��Ϣ

			listMaps.add(rowMap);

		}
		objectMapper.registerModule(new Hibernate4Module());
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData(listMaps);
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	private List<ImgSpec> specs = new ArrayList<ImgSpec>();

	public List<ImgSpec> getSpecs() {
		return specs;
	}

	public void setSpecs(List<ImgSpec> specs) {
		this.specs = specs;
	}

	/**
	 * �ύ��������ͼƬ
	 * ÿ���ύһ��
	 * @return
	 * data:
	 * @throws Exception
	 */
	public String orderPicUpload() throws Exception {
		//�����ϴ�ͼƬ����·��
		String orderPicPath = "E:\\TOMCAT����Ŀ¼\\";
		//�ϴ�ͼƬ
		List<ImgSpec> imgSpecs = upload(spTreasureimg_pic, orderPicPath);
		//ֻ��һ��
		//�������쳣
		if (imgSpecs != null && imgSpecs.size() == 1) {
			ImgSpec imgSpec = imgSpecs.get(0);
			SpAotuerpTreasureImg treasureImg = new SpAotuerpTreasureImg();
			treasureImg.setSpImg(imgSpec.getImgPath());//ԭͼƬ
			treasureImg.setSpImgsize(imgSpec.getImgSize());//��С
			treasureImg.setSpImgwidth(imgSpec.getImgWidth());//��
			treasureImg.setSpImgheight(imgSpec.getImgHeight());//��
			//��������ͼ
			if (specs != null && specs.size() > 0) {
				List<ImgSpec> thumbnailSpecs = createThumbnailImg(specs, imgSpec.getImgPath(), orderPicPath);
				for (ImgSpec thumbnailSpec : thumbnailSpecs) {
					SpAotuerpTreasureImg thumbnail = new SpAotuerpTreasureImg();
					thumbnail.setSpImg(thumbnailSpec.getImgPath());//����ͼ
					thumbnail.setSpImgsize(thumbnailSpec.getImgSize());//��С
					thumbnail.setSpImgwidth(thumbnailSpec.getImgWidth());//��
					thumbnail.setSpImgheight(thumbnailSpec.getImgHeight());//��
					treasureImg.getThumbnails().add(thumbnail);
				}
				iSpAotuerpTreasureService.saveSpAotuerpTreasureImg(treasureImg);
				imgSpec.setImgId(treasureImg.getSpId());//ͼƬid
				imgSpec.setPostfix(".jpg");//����ͼ��׺
				jsonResult.setCode(0);
				jsonResult.setMsg("����ɹ�");
				jsonResult.setData(imgSpec);
			}
		} else {//����ļ�������ʧ��
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����ͼƬid
	private Integer[] imgIds;
	//��ͼ
	private Integer imgPrimaryId;

	/**
	 * �����ύ����
	 * ״̬Ĭ��δ���
	 * @return
	 * @throws Exception
	 */
	public String orderSubmit() throws Exception {
		//��ѯ�����Ʒ
		SpProductBinfo spProductBinfo = iCommodityService.getById(spProductId);
		if (spProductBinfo != null) {
			for (SpAotuerpTreasureProductSku spAotuerpTreasureProductSku : spAotuerpTreasureInfo
					.getSpAotuerpTreasureProductSkus()) {
				//��Ӧ��sku���������
				for (SpProductSku spProductSku : spProductBinfo.getSpProductSkus()) {
					if (spProductSku.getSpSkuid().equals(spAotuerpTreasureProductSku.getSpProductSku().getSpSkuid())) {
						spProductSku.setSpPdcount(spProductSku.getSpPdcount()
								- spAotuerpTreasureProductSku.getSpSalesquantity());
					}
				}
				//�ܼ�
				spAotuerpTreasureProductSku.setSpTotalprice(spAotuerpTreasureProductSku.getSpSalesprice()
						* spAotuerpTreasureProductSku.getSpSalesquantity());
				//��������
				spAotuerpTreasureProductSku.setSpSalesorderquantity(spAotuerpTreasureProductSku.getSpSalesquantity());
			}
			//ͼƬ
			//�־û�ͼƬ����
			List<SpAotuerpTreasureImg> treausreImgs=iSpAotuerpTreasureImgService.getByIds(imgIds);
			Integer i_o=1;//˳��
			//����Ϊ��ͼ
			for (SpAotuerpTreasureImg treasureImg : treausreImgs) {
				if(imgPrimaryId!=null&&imgPrimaryId.equals(treasureImg.getSpId())){
					treasureImg.setSpIsprimary(1);
				}
				treasureImg.setSpImgorder(i_o++);
			}
			spAotuerpTreasureInfo.setSpAotuerpTreasureImgs(new HashSet<SpAotuerpTreasureImg>(treausreImgs));
			spAotuerpTreasureInfo.setSpProductBinfo(spProductBinfo);
			iSpAotuerpTreasureService.save(spAotuerpTreasureInfo);
			//������Ʒ����
			iCommodityService.update(spProductBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		} else {
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//�ϼܱ�����ѯ
	public String treasureShelvesOrderList() throws Exception {
		return "treasureShelvesOrderList";

	}

	public String treasureShelvesOrderListData() throws Exception {
		PageBean<SpAotuerpTreasureInfo> spAotuerpTreasureInfoList = iSpAotuerpTreasureService
				.findSpAotuerpTreasureInfoList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpTreasureInfo.getSpId());//�������
			rowMap.put("sp_idShow", spAotuerpTreasureInfo.getSpId());//�������
			rowMap.put("sp_Treasuretitle", spAotuerpTreasureInfo.getSpTreasuretitle());//��������
			rowMap.put("sp_TreasureStatus", spAotuerpTreasureInfo.getSpAotuerpTreasureStatus().getSpTreasurestatus());//����״̬
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpTreasureInfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//�������
	public String treasureShelvesOrderCheck() throws Exception {
		List<SpAotuerpTreasureInfo> spAotuerpTreasureInfos = iSpAotuerpTreasureService.getByIds(spAotuerpTreasureIds);
		if (spAotuerpTreasureInfos != null && spAotuerpTreasureInfos.size() > 0) {
			for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfos) {
				SpAotuerpTreasureStatus spAotuerpTreasureStatus = new SpAotuerpTreasureStatus();
				if (treasureOrderCheck) {
					spAotuerpTreasureStatus.setSpId(2);
					spAotuerpTreasureInfo.setSpAotuerpTreasureStatus(spAotuerpTreasureStatus);
					;//ͨ��
				} else {
					spAotuerpTreasureStatus.setSpId(3);
					spAotuerpTreasureInfo.setSpAotuerpTreasureStatus(spAotuerpTreasureStatus);//ʧ��
				}
				iSpAotuerpTreasureService.update(spAotuerpTreasureInfo);
			}
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		} else {
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	/** 
	 * �ݹ�õ���ǰ�ڵ�����и��ڵ� 
	 * @param configId ��ǰ�ڵ� 
	 * @return ���и��ڵ� 
	 * @throws Exception �׳����쳣 
	 */
	public String getParentName(SpProductCategory spProductCategory) throws Exception {
		//�����ݿ⽻��,�õ���ǰ�ڵ��¼  
		if (spProductCategory != null) {
			String configName = spProductCategory.getSpCategoryn() + ">";
			//����patrolConfigEntity.getConfigParentId()��ʾ��ǰ�ڵ�ĸ��ڵ�ID  
			String returnConfigName = getParentName(spProductCategory.getSpProductCategory());
			return returnConfigName + configName;
		} else {
			return "";
		}
	}

	public Integer[] getSpProductSkuIds() {
		return spProductSkuIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpProductSkuIds(Integer[] spProductSkuIds) {
		this.spProductSkuIds = spProductSkuIds;
	}

	public Integer getSpProductId() {
		return spProductId;
	}

	public void setSpProductId(Integer spProductId) {
		this.spProductId = spProductId;
	}

	public SpAotuerpTreasureInfo getSpAotuerpTreasureInfo() {
		return spAotuerpTreasureInfo;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureInfo(SpAotuerpTreasureInfo spAotuerpTreasureInfo) {
		this.spAotuerpTreasureInfo = spAotuerpTreasureInfo;
	}

	public Integer[] getSpAotuerpTreasureIds() {
		return spAotuerpTreasureIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureIds(Integer[] spAotuerpTreasureIds) {
		this.spAotuerpTreasureIds = spAotuerpTreasureIds;
	}

	public boolean isTreasureOrderCheck() {
		return treasureOrderCheck;
	}

	@JSON(serialize = true, deserialize = true)
	public void setTreasureOrderCheck(boolean treasureOrderCheck) {
		this.treasureOrderCheck = treasureOrderCheck;
	}

	public UpLoad getSpTreasureimg_pic() {
		return spTreasureimg_pic;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpTreasureimg_pic(UpLoad spTreasureimg_pic) {
		this.spTreasureimg_pic = spTreasureimg_pic;
	}

	public Integer[] getImgIds() {
		return imgIds;
	}

	public void setImgIds(Integer[] imgIds) {
		this.imgIds = imgIds;
	}

	public Integer getImgPrimaryId() {
		return imgPrimaryId;
	}

	public void setImgPrimaryId(Integer imgPrimaryId) {
		this.imgPrimaryId = imgPrimaryId;
	}

}
