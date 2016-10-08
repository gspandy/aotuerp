package com.aotuspace.aotuerp.web.sppurchase.view.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseListPd;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrderPd;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrders;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersNumbers;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersStatus;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.util.CustomUtil;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

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
public class PurchaseAction extends BaseAction<SpAotuerpPurchaseOrders> {

	//��������
	private SpAotuerpPurchaseOrders spAotuerpPurchaseOrders = new SpAotuerpPurchaseOrders();

	//������
	private SpAotuerpPurchaseList spAotuerpPurchaseList = new SpAotuerpPurchaseList();

	private String spPurchaseId;

	//��Ϣҳ��
	public String list() throws Exception {
		return "list";
	}

	//��������
	public String purchaseOrdersAdd() throws Exception {
		//�������
		Integer poNum = iSpAotuerpPurchaseOrdersService.findByMaxPurchaseOrdersNum();
		SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers = new SpAotuerpPurchaseOrdersNumbers();
		if (poNum != null) {
			spAotuerpPurchaseOrdersNumbers.setSpPurchaseOrderId("JHDD" + CustomUtil.getCurrCalendar("yyyyMMdd") + "00"
					+ (poNum + 1));
			iSpAotuerpPurchaseOrdersService.save(spAotuerpPurchaseOrdersNumbers);
			ActionContext.getContext().put("PurchaseOrdersNum", spAotuerpPurchaseOrdersNumbers);
		} else {
			spAotuerpPurchaseOrdersNumbers
					.setSpPurchaseOrderId("JHDD" + CustomUtil.getCurrCalendar("yyyyMMdd") + "001");
			iSpAotuerpPurchaseOrdersService.save(spAotuerpPurchaseOrdersNumbers);
			ActionContext.getContext().put("PurchaseOrdersNum", spAotuerpPurchaseOrdersNumbers);
		}
		//��������
		ActionContext.getContext().put("CreateDate", CustomUtil.getCurrCalendar("yyyy-MM-dd"));
		return "purchaseOrdersAdd";
	}

	//��������(�ύ)
	public String purchaseOrdersSubmit() throws Exception {
		Long totalOrder = (long) 0;
		Date curentDate = new Date();
		//System.out.println(model);
		//�������
		SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers = iSpAotuerpPurchaseOrdersService
				.getById(spAotuerpPurchaseOrders.getSpAotuerpPurchaseOrdersNumbers().getSpId());
		if (spAotuerpPurchaseOrdersNumbers != null) {
			//������ͬ��
			//������λ
			//������
			//�ֿ�
			//��������
			spAotuerpPurchaseOrders.setSpCreatedate(new Date(curentDate.getTime()));
			//Ԥ�Ƶ�������
			//����������Ʒid
			//�������
			for (SpAotuerpPurchaseOrderPd spAotuerpPurchaseOrderPd : spAotuerpPurchaseOrders
					.getSpAotuerpPurchaseOrderPds()) {
				spAotuerpPurchaseOrderPd.setSpTotalprice(spAotuerpPurchaseOrderPd.getSpUnitprice()
						* spAotuerpPurchaseOrderPd.getSpQuantity());
				totalOrder += spAotuerpPurchaseOrderPd.getSpTotalprice();
			}
			//System.out.println(totalOrder);
			spAotuerpPurchaseOrders.setSpOrderamount(totalOrder);
			//���ʱ��
			//����
			//��ע
			/*Set<SpAotuerpPurchaseOrders> spAotuerpPurchaseOrdersSet=new HashSet<SpAotuerpPurchaseOrders>();
			spAotuerpPurchaseOrdersSet.add(spAotuerpPurchaseOrders);
			spAotuerpPurchaseOrdersNumbers.setSpAotuerpPurchaseOrderses(spAotuerpPurchaseOrdersSet);*/
			spAotuerpPurchaseOrdersNumbers.setSpAotuerpPurchaseOrders(spAotuerpPurchaseOrders);
			iSpAotuerpPurchaseOrdersService.update(spAotuerpPurchaseOrdersNumbers);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}
		return NONE;
	}

	//�������
	public String purchaseStorageOrdersAdd() throws Exception {
		ActionContext.getContext().put("CreateDate", CustomUtil.getCurrCalendar("yyyy-MM-dd"));
		return "purchaseStorageOrdersAdd";
	}

	//������(�ύ)
	public String purchaseStorageOrdersSubmit() throws Exception {
		Long totalOrder = (long) 0;
		//����sku��Ʒ�ܼ�
		for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList.getSpAotuerpPurchaseListPds()) {
			spAotuerpPurchaseListPd.setSpTotalprice(spAotuerpPurchaseListPd.getSpUnitprice()
					* spAotuerpPurchaseListPd.getSpQuantity());
			totalOrder += spAotuerpPurchaseListPd.getSpTotalprice();
		}
		//������
		spAotuerpPurchaseList.setSpPaymentamount(totalOrder);
		//�Ƿ񿪷�Ʊ�����治Ҫ��
		spAotuerpPurchaseList.setSpIsinvoice(0);
		spAotuerpPurchaseList.setSpAotuerpPurchaseOrders(iSpAotuerpPurchaseOrdersService
				.getSpAotuerpPurchaseOrdersById(spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().getSpId()));
		iSpAotuerpPurchaseStorageOrdersService.save(spAotuerpPurchaseList);
		//������Ϊ��ִ��
		SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus = new SpAotuerpPurchaseOrdersStatus();
		spAotuerpPurchaseOrdersStatus.setSpId(2);
		spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().setSpAotuerpPurchaseOrdersStatus(
				spAotuerpPurchaseOrdersStatus);
		iSpAotuerpPurchaseStorageOrdersService.update(spAotuerpPurchaseList);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����������Ϣ
	public String purchaseOrdersListData() throws Exception {
		PageBean<SpAotuerpPurchaseOrdersNumbers> spAotuerpPurchaseOrdersNumbersList = iSpAotuerpPurchaseOrdersService
				.findSpAotuerpPurchaseOrdersNumbersListNotFinish(rows, page);
		objectMapper.registerModule(new Hibernate4Module());
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers : spAotuerpPurchaseOrdersNumbersList
				.getRecordList()) {
			if (spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders() != null) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("sp_PurchaseOrderNumber_Id", spAotuerpPurchaseOrdersNumbers.getSpId());//����������
				rowMap.put("sp_PurchaseOrderId", spAotuerpPurchaseOrdersNumbers.getSpPurchaseOrderId());//�������
				rowMap.put("sp_PurchaseOrder_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpId());//�������
				rowMap.put("sp_Createdate", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpCreatedate());//����������������
				rowMap.put("sp_Planarrivaldate", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpPlanarrivaldate());//Ԥ�Ƶ�������
				rowMap.put("sp_Supplier", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpSupplierBinfo().getSpSupplierDinfo().getSpSusup());//������λ
				rowMap.put("sp_Supplier_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpSupplierBinfo().getSpSupplierBinfoKey().getSpId()
						+ "-"
						+ spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpSupplierBinfo()
								.getSpSupplierBinfoKey().getSpSuid());//������λ
				rowMap.put("sp_Warehousename", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpWarehouseInfo().getSpWarehousename());
				rowMap.put("sp_Warehousename_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpWarehouseInfo().getSpId());
				rowMap.put("sp_Employee", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeDinfo().getSpEprealname());//������
				rowMap.put("sp_Employee_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeBinfoKey().getSpId()
						+ "-"
						+ spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpEmployeeBinfo()
								.getSpEmployeeBinfoKey().getSpEpid());//������
				rowMap.put("sp_EmployeeDepart", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeDepart().getSpEpdepartn());//����
				rowMap.put("sp_Orderamount", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpOrderamount());//���
				rowMap.put("sp_PurchaseOrdersStatus", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrdersStatus().getSpStatusn());//����
				rowMap.put("sp_Remark", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpRemark());//��ע
				for (SpAotuerpPurchaseOrderPd spAotuerpPurchaseOrderPd : spAotuerpPurchaseOrdersNumbers
						.getSpAotuerpPurchaseOrders().getSpAotuerpPurchaseOrderPds()) {
					System.out.println(spAotuerpPurchaseOrderPd.getSpProductSku().getSpProductBinfo()
							.getSpProductBrands().getSpProductCategory());
				}
				rowMap.put("sp_PurchaseOrderPds", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrderPds());//��Ʒ
				listMaps.add(rowMap);
			}
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpPurchaseOrdersNumbersList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//��Ʒ��ϢlistData����
	public String listData() throws Exception {
		PageBean<SpProductSku> spProductSkuList = iCommodityService.findSpProductSkuList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkuList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spProductSku.getSpSkuid());//��Ʒskuid
			rowMap.put("sp_idShow", spProductSku.getSpSkuid());//��Ʒskuid
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//��Ʒ����
			rowMap.put("sp_Pdproname", spProductSku.getSpSkupropertiesname());//����ͺ�
			rowMap.put("sp_Pdbrand", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//Ʒ��
			rowMap.put("sp_PdCategory", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//��Ŀ��
			rowMap.put("sp_UnitPrice", spProductSku.getSpPdprice());//����
			rowMap.put("sp_Quantity", spProductSku.getSpPdcount());//����
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spProductSkuList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//������ѯҳ��
	public String purchaseStorageInquire() throws Exception {
		return "purchaseStorageInquire";
	}

	//��������Ϣ
	public String purchaseStorageOrdersListData() throws Exception {
		PageBean<SpAotuerpPurchaseList> spAotuerpPurchaseList = iSpAotuerpPurchaseOrdersService
				.findSpAotuerpPurchaseList(rows, page);
		objectMapper.registerModule(new Hibernate4Module());

		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpPurchaseList spAotuerpPurchaseList2 : spAotuerpPurchaseList.getRecordList()) {
			if (spAotuerpPurchaseList2.getSpAotuerpPurchaseOrders() != null) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("sp_PurchaseStorageOrderId", spAotuerpPurchaseList2.getSpPurchaseId());//���������
				rowMap.put("sp_PurchaseOrderId", spAotuerpPurchaseList2.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrdersNumbers().getSpPurchaseOrderId());//�������
				rowMap.put("sp_Createdate", spAotuerpPurchaseList2.getSpCreatedate());//��������������
				rowMap.put("sp_Supplier", spAotuerpPurchaseList2.getSpSupplierBinfo().getSpSupplierDinfo().getSpSusup());//������λ
				rowMap.put("sp_Warehousename", spAotuerpPurchaseList2.getSpAotuerpWarehouseInfo().getSpWarehousename());//�ֿ�
				rowMap.put("sp_Employee", spAotuerpPurchaseList2.getSpEmployeeBinfo().getSpEmployeeDinfo()
						.getSpEprealname());//������
				rowMap.put("sp_EmployeeDepart", spAotuerpPurchaseList2.getSpEmployeeBinfo().getSpEmployeeDepart()
						.getSpEpdepartn());//����
				rowMap.put("sp_Orderamount", spAotuerpPurchaseList2.getSpPaymentamount());//���
				for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList2
						.getSpAotuerpPurchaseListPds()) {
					System.out.println(spAotuerpPurchaseListPd.getSpProductSku().getSpProductBinfo()
							.getSpProductBrands().getSpProductCategory());
				}
				rowMap.put("sp_PurchaseOrderPds", spAotuerpPurchaseList2.getSpAotuerpPurchaseListPds());//��Ʒ
				rowMap.put("sp_Remark", spAotuerpPurchaseList2.getSpRemark());//��ע
				listMaps.add(rowMap);
			}
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spAotuerpPurchaseList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//�����������
	public String purchaseStorageOrdersCheck() throws Exception {
		//��ѯ������ԭ����
		if(spPurchaseId!=null&&spPurchaseId!=""){
			SpAotuerpPurchaseList spAotuerpPurchaseList = iSpAotuerpPurchaseStorageOrdersService
					.findSpAotuerpPurchaseListByPurchaseId(spPurchaseId);			
			for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList.getSpAotuerpPurchaseListPds()) {
				//������sku��Ʒ��������(������϶����ϵ�����)
				spAotuerpPurchaseListPd.getSpProductSku().setSpPdcount(spAotuerpPurchaseListPd.getSpProductSku().getSpPdcount()+spAotuerpPurchaseListPd.getSpQuantity());
				//�ƶ�����е�sku��Ʒ��������
				//1,ָ���������sku��Ʒ��Ϣ
				//2,ָ�������û��sku��Ʒ��Ϣ
				boolean flag=true;
				for (SpAotuerpProductStocks spAotuerpProductStocks : spAotuerpPurchaseList.getSpAotuerpWarehouseInfo().getSpAotuerpProductStockses()) {
					//��sku��Ʒid
					//�жϽ�����sku��Ʒ�Ƿ���ڿ���е���Ʒ
					if(spAotuerpPurchaseListPd.getSpProductSku().getSpSkuid().equals(spAotuerpProductStocks.getSpProductSku().getSpSkuid())){
						//����У��Ͱѿ���е�sku��Ʒ��������
						spAotuerpProductStocks.setSpQuantity(spAotuerpProductStocks.getSpQuantity()+spAotuerpPurchaseListPd.getSpQuantity());
						//��ִ�вֿ�����sku��Ʒ
						flag=false;
					}
				}
				//���û��sku��Ʒ
				if(flag){
					//�µĲֿ�sku��Ʒ
					SpAotuerpProductStocks spAotuerpProductStocks=new SpAotuerpProductStocks();
					spAotuerpProductStocks.setSpProductSku(spAotuerpPurchaseListPd.getSpProductSku());
					spAotuerpProductStocks.setSpQuantity(spAotuerpPurchaseListPd.getSpQuantity());
					spAotuerpPurchaseList.getSpAotuerpWarehouseInfo().getSpAotuerpProductStockses().add(spAotuerpProductStocks);
				}
			}
			
			//������������ִ�����
			SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus=new SpAotuerpPurchaseOrdersStatus();
			spAotuerpPurchaseOrdersStatus.setSpId(3);
			spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().setSpAotuerpPurchaseOrdersStatus(spAotuerpPurchaseOrdersStatus);
			
			iSpAotuerpPurchaseStorageOrdersService.update(spAotuerpPurchaseList);
			
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ�ܣ���������");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}
		return NONE;
	}

	//getter setter
	public SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrders() {
		return spAotuerpPurchaseOrders;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpPurchaseOrders(SpAotuerpPurchaseOrders spAotuerpPurchaseOrders) {
		this.spAotuerpPurchaseOrders = spAotuerpPurchaseOrders;
	}

	public SpAotuerpPurchaseList getSpAotuerpPurchaseList() {
		return spAotuerpPurchaseList;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpPurchaseList(SpAotuerpPurchaseList spAotuerpPurchaseList) {
		this.spAotuerpPurchaseList = spAotuerpPurchaseList;
	}

	public String getSpPurchaseId() {
		return spPurchaseId;
	}

	public void setSpPurchaseId(String spPurchaseId) {
		this.spPurchaseId = spPurchaseId;
	}

}
