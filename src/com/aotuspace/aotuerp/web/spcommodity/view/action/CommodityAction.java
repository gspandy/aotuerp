package com.aotuspace.aotuerp.web.spcommodity.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.model.ProductPro;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBproperties;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBrands;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyName;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyValue;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.spcommodity.model.Commodity;
import com.aotuspace.aotuerp.web.spcommodity.model.sp_sku;
import com.aotuspace.aotuerp.web.util.CustomUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:CommodityAction
 * Description:��Ʒ����ģ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����3:22:22
 *
 */
@Controller
@Scope("prototype")
public class CommodityAction extends BaseAction<SpProductBinfo> {

	public Integer topCategoryId;//������ĿID

	public Integer secondCategoryId;//������ĿID

	public Integer thirdCategoryId;//������ĿID������ΪƷ�ƣ�ʵ���Ǹ������ѡ����Ӧ�����ԣ�
	
	public Commodity commodity;
	
	public Integer[] commodityIds;//��Ʒids
	

	//��Ʒ��Ϣҳ��
	public String list() throws Exception {
		return "list";
	}

	//��Ʒ��ϢlistData����
	public String listData() throws Exception {
		PageBean<SpProductBinfo> spProductBinfoList = iCommodityService.findSpProductBinfoList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductBinfo spProductBinfo : spProductBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spProductBinfo.getSpId());
			rowMap.put("sp_idShow", spProductBinfo.getSpId());
			rowMap.put("sp_Pdspu", spProductBinfo.getSpPdspu());//��Ʒ����
			rowMap.put("sp_Brandn", spProductBinfo.getSpProductBrands().getSpBrandn());//Ʒ��
			rowMap.put("sp_Categoryn", spProductBinfo.getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//���
			rowMap.put("sp_Pdcredate", spProductBinfo.getSpPdcredate());//��������
			listMaps.add(rowMap);
		}
		/*PageBean<SpProductSku> spProductSkuList = iCommodityService.findSpProductSkuList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkuList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_Skuid", spProductSku.getSpSkuid());
			rowMap.put("sp_SkuidShow", spProductSku.getSpSkuid());
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//��Ʒ����
			rowMap.put("sp_Brandn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//Ʒ��
			rowMap.put("sp_Categoryn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//���
			rowMap.put("sp_Pdcredate", spProductSku.getSpPdcredate());//��������
			rowMap.put("sp_Statusn", spProductSku.getSpProductStatus().getSpStatusn());//��Ʒ״̬
			listMaps.add(rowMap);
		}*/
		//easyui total ����  rows�б�
		pageListMap.put("total", spProductBinfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//������Ʒid��ѯsku��Ϣ
	public String skuListData()throws Exception{
		SpProductBinfo spProductBinfo = iCommodityService.getById(model.getSpId());
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductBinfo.getSpProductSkus()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_Skuid", spProductSku.getSpSkuid());
			rowMap.put("sp_SkuidShow", spProductSku.getSpSkuid());
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//��Ʒ����
			rowMap.put("sp_Pdproname", spProductSku.getSpSkupropertiesname());
			rowMap.put("sp_Brandn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//Ʒ��
			rowMap.put("sp_Categoryn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//���
			rowMap.put("sp_Quantity", spProductSku.getSpPdcount());//����
			listMaps.add(rowMap);
		}
		pageListMap.put("total", spProductBinfo.getSpProductSkus().size());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	

	//������Ŀ
	public String categoryTopData() throws Exception {
		List<SpProductCategory> spProductCategories = iCommodityCategoryService.findCategoryTopList();
		objectMapper.registerModule(new Hibernate4Module());//���ӹ�ϵ�޷����л�
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//��Ϊ��
		resp.getWriter().write(objectMapper.writeValueAsString(spProductCategories));
		return NONE;
	}

	//������Ŀ
	public String categorySecondData() throws Exception {
		List<SpProductCategory> spProductCategories = iCommodityCategoryService.findCategoryByTopList(topCategoryId);
		objectMapper.registerModule(new Hibernate4Module());//���ӹ�ϵ�޷����л�
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//��Ϊ��
		resp.getWriter().write(objectMapper.writeValueAsString(spProductCategories));
		return NONE;
	}

	//Ʒ�Ʋ�ѯ
	public String brandsData() throws Exception {
		List<SpProductBrands> spProductBrands = iCommodityBrandsService.findBrandsByCategoryId(secondCategoryId);
		objectMapper.registerModule(new Hibernate4Module());//���ӹ�ϵ�޷����л�
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//��Ϊ��
		resp.getWriter().write(objectMapper.writeValueAsString(spProductBrands));
		return NONE;
	}

	//���Բ�ѯ
	public String proData() throws Exception {
		//����Ʒ�Ʋ�ѯ������������ѯ����ֵ��ƴ��json
		List<SpProductPropertyName> spProductPropertyNames = iCommodityPropertiesService
				.findPropertiesByCategoryId(thirdCategoryId);
		//ƴ��json
		List<ProductPro> spProductSkuPro =new ArrayList<ProductPro>();//sku
		List<ProductPro> spProductSpuPro =new ArrayList<ProductPro>();//spu
		for (SpProductPropertyName spProductPropertyName : spProductPropertyNames) {
			String sp_InputType = "";
			 if(spProductPropertyName.getSpIssalepro()==0&&spProductPropertyName.getSpIskeypro()==0){//��������
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "������";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "�����";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "��ѡ��";
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						defaultValue(spProductPropertyName.getSpProductPropertyValues()),
						spProductPropertyName.getSpIsrequirepro(),"sp_spu_pro_tbodyAppend");
				spProductSpuPro.add(productPro);
			}else if (spProductPropertyName.getSpIssalepro() == 1) {//��������
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "������";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "�����";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "��ѡ��";
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						defaultValue(spProductPropertyName.getSpProductPropertyValues()),
						spProductPropertyName.getSpIsrequirepro(),"sp_sku_pro_tbodyAppend");
				spProductSkuPro.add(productPro);
			}
		};
		//objectMapper.setSerializationInclusion(Include.NON_EMPTY);//��Ϊ���ַ����������� vo�������
		resp.getWriter().write("["+objectMapper.writeValueAsString(spProductSpuPro)
				+","+objectMapper.writeValueAsString(spProductSkuPro)+"]");
		return NONE;
	}
	
	//�����Ʒ
	public String add() throws Exception{
		//������Ʒ
		SpProductBinfo spProductBinfo=new SpProductBinfo();
		//��Ʒ����
		spProductBinfo.setSpPdspu(commodity.getSp_spu_title());
		//��ƷƷ��
		spProductBinfo.setSpProductBrands(new SpProductBrands(Integer.valueOf(commodity.getSp_spu_brand())));
		//��Ʒ����
		spProductBinfo.setSpPdtitle("helloo");
		//��Ʒ��������
		spProductBinfo.setSpPdfeature("hello");
		//��ƷͼƬ//Ĭ��
		
		Set<SpProductSku> spProductSkus = new HashSet<SpProductSku>();
		Set<SpProductBproperties> spProductBproperties=new HashSet<SpProductBproperties>();
		//sku
		for (sp_sku sku : commodity.getSp_sku()) {
			//����sku
			SpProductSku spProductSku=new SpProductSku();
			spProductSku.setSpPdprice(Long.valueOf(sku.getSp_PdPrice()));//�۸�
			spProductSku.setSpPdcount(Integer.valueOf(sku.getSp_PdCount()));//����
			//skuֵ�ԡ�;������
			spProductSku.setSpSkuproperties(skuHandler(sku.getSp_sku()));//sku
			spProductSku.setSpSkupropertiesname(sku.getSp_sku_name());//sku����
			spProductSkus.add(spProductSku);
			//��sku�����������
			for (String skuString : sku.getSp_sku()) {
				SpProductBproperties spProductBproperties2=new SpProductBproperties();//spu
				SpProductPropertyName spProductPropertyName=iCommodityPropertiesService.getById(Integer.valueOf(spuHandler(skuString)[0]));
				spProductBproperties2.setSpProductPropertyName(spProductPropertyName);//spu_name
				for (SpProductPropertyValue spProductPropertyValue : spProductPropertyName.getSpProductPropertyValues()) {
					if(spProductPropertyValue.getSpId().equals(Integer.valueOf(spuHandler(skuString)[1]))){
						spProductBproperties2.setSpProductPropertyValue(spProductPropertyValue);//spu_value
						break;
					}
				}
				spProductBproperties2.setSpIssku(1);//�Ƿ�sku
				spProductBproperties2.setSpProductSku(spProductSku);
				spProductBproperties.add(spProductBproperties2);
			}
		}
		spProductBinfo.setSpProductSkus(spProductSkus);
		//spu
		for (String spu : commodity.getSp_spu()) {
			SpProductBproperties spProductBproperties2=new SpProductBproperties();//spu
			String[] spu2=spuHandler(spu);
			SpProductPropertyName spProductPropertyName=iCommodityPropertiesService.getById(Integer.valueOf(spu2[0]));
			spProductBproperties2.setSpProductPropertyName(spProductPropertyName);
			for (SpProductPropertyValue spProductPropertyValue : spProductPropertyName.getSpProductPropertyValues()) {
				if(spProductPropertyValue.getSpId().equals(Integer.valueOf(Integer.valueOf(spu2[1])))){
					spProductBproperties2.setSpProductPropertyValue(spProductPropertyValue);//spu_value
					break;
				}
			}
			spProductBproperties.add(spProductBproperties2);
		}
		spProductBinfo.setSpProductBproperties(spProductBproperties);
		iCommodityService.save(spProductBinfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");	
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//��ȡ����ֵ����
	public String defaultValue(Set<SpProductPropertyValue> spProductPropertyValues) throws JsonProcessingException{
		String[] name=new String[spProductPropertyValues.size()];
		String[] value=new String[spProductPropertyValues.size()];
		int index=0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValues) {
			name[index]=spProductPropertyValue.getSpPropertyvalue();
			value[index++]=spProductPropertyValue.getSpProductPropertyName().getSpId()+"-"+String.valueOf(spProductPropertyValue.getSpId());
		}
		return CustomUtil.stringsToString(name)+","+CustomUtil.stringsToString(value);
	}
	
	
	//ɾ����Ʒ
	public String delete() throws Exception {
		//ɾ����Ʒ
		iCommodityService.delete(commodityIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//�޸���Ʒ
	public String edit() throws Exception {
		//��ȡ��Ʒ��Ϣ
		SpProductBinfo spProductBinfo = iCommodityService.getById(model.getSpId());
		//����Ʒ�Ʋ�ѯ������������ѯ����ֵ��ƴ��json
		List<SpProductPropertyName> spProductPropertyNames = iCommodityPropertiesService
				.findPropertiesByCategoryId(spProductBinfo.getSpProductBrands().getSpProductCategory().getSpId());
		//ƴ��json
		List<ProductPro> spProductSkuPro = new ArrayList<ProductPro>();//sku
		List<ProductPro> spProductSpuPro = new ArrayList<ProductPro>();//spu
		for (SpProductPropertyName spProductPropertyName : spProductPropertyNames) {
			String sp_InputType = "";
			if (spProductPropertyName.getSpIssalepro() == 0 && spProductPropertyName.getSpIskeypro() == 0) {//��������
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "������";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "�����";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "��ѡ��";
				Set<SpProductPropertyValue> spProductPropertyValues =new HashSet<SpProductPropertyValue>();
				//��������
				for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
					if(spProductPropertyName.getSpId().equals(spProductBproperties.getSpProductPropertyName().getSpId())){
						spProductPropertyValues.add(spProductBproperties.getSpProductPropertyValue());
					}
				}	
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						setDefaultValue(spProductPropertyName.getSpProductPropertyValues(),spProductPropertyValues),
						spProductPropertyName.getSpIsrequirepro(), "sp_spu_pro_tbodyAppend_edit");
				spProductSpuPro.add(productPro);
				
			} else if (spProductPropertyName.getSpIssalepro() == 1) {//��������
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "������";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "�����";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "��ѡ��";
				Set<SpProductPropertyValue> spProductPropertyValues =new HashSet<SpProductPropertyValue>();
				//��������
				for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
					if(spProductPropertyName.getSpId().equals(spProductBproperties.getSpProductPropertyName().getSpId())){
						spProductPropertyValues.add(spProductBproperties.getSpProductPropertyValue());
					}
				}	
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						setDefaultValue(spProductPropertyName.getSpProductPropertyValues(),spProductPropertyValues),
						spProductPropertyName.getSpIsrequirepro(), "sp_sku_pro_tbodyAppend_edit");
				spProductSkuPro.add(productPro);
			}
		}
		//sku����
		resp.getWriter().write("["+objectMapper.writeValueAsString(spProductSpuPro)
				+","+objectMapper.writeValueAsString(spProductSkuPro)+"]");
		return NONE;
	}
	
	//��ȡ����ֵ����
	public String setDefaultValue(Set<SpProductPropertyValue> spProductPropertyValues,Set<SpProductPropertyValue> spProductPropertyValueDefault) throws JsonProcessingException {
		String[] name = new String[spProductPropertyValues.size()];
		String[] value = new String[spProductPropertyValues.size()];
		int index = 0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValues) {
			name[index] = spProductPropertyValue.getSpPropertyvalue();
			value[index++] = spProductPropertyValue.getSpProductPropertyName().getSpId() + "-"
					+ String.valueOf(spProductPropertyValue.getSpId());
		}
		String[] defaultValue=new String[spProductPropertyValueDefault.size()];
		index = 0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValueDefault) {
			defaultValue[index++]=spProductPropertyValue.getSpProductPropertyName().getSpId()+"-"+String.valueOf(spProductPropertyValue.getSpId());
		}
		return CustomUtil.stringsToString(name) + "," + CustomUtil.stringsToString(value)+","+CustomUtil.stringsToString(defaultValue);
	}
	
	
	public static String[] skuHandler(String string) {
		String[] s = string.split("|");
		return s;
	}
	
	//sku�ԡ�;���Ÿ���
	public static String skuHandler(List<String> sku){
		if (sku == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : sku) {
			if (flag) {
				result.append(";");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
	public static String[] spuHandler(String string) {
		String[] s = string.split("-");
		return s;
	}
	
	public Integer getTopCategoryId() {
		return topCategoryId;
	}

	public void setTopCategoryId(Integer topCategoryId) {
		this.topCategoryId = topCategoryId;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getThirdCategoryId() {
		return thirdCategoryId;
	}

	public void setThirdCategoryId(Integer thirdCategoryId) {
		this.thirdCategoryId = thirdCategoryId;
	}
	
	public Commodity getCommodity() {
		return commodity;
	}

	@JSON(serialize = true, deserialize = true)
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer[] getCommodityIds() {
		return commodityIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setCommodityIds(Integer[] commodityIds) {
		this.commodityIds = commodityIds;
	}

	
}
