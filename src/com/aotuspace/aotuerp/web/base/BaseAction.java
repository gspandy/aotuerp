package com.aotuspace.aotuerp.web.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.aotuspace.aotuerp.web.model.ImgSpec;
import com.aotuspace.aotuerp.web.model.JsonResult;
import com.aotuspace.aotuerp.web.model.UpLoad;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityBrandsService;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityCategoryService;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityPropertiesService;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityService;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysMMService;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysPMService;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpAotuerpPurchaseOrdersService;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpAotuerpPurchaseStorageOrdersService;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpSupplierService;
import com.aotuspace.aotuerp.web.sptreasure.service.ISpAotuerpTreasureImgService;
import com.aotuspace.aotuerp.web.sptreasure.service.ISpAotuerpTreasureService;
import com.aotuspace.aotuerp.web.sptreasure.service.ISpAotuerpTreasureTaskModeService;
import com.aotuspace.aotuerp.web.sptreasure.service.ISpAotuerpTreasureTaskService;
import com.aotuspace.aotuerp.web.spwarehouse.service.IWarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * Title:BaseAction Description: Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 ����12:17:41
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, ServletResponseAware {

	// ============�ж�������ʱ��Ҫ�õ��Ĳ���===========
	protected Integer twoNav;

	public Integer getTwoNav() {
		return twoNav;
	}

	public void setTwoNav(Integer twoNav) {
		this.twoNav = twoNav;
	}

	// =============JsonResult����===============
	protected JsonResult jsonResult = new JsonResult();

	// =============jackson josnת����===========
	protected ObjectMapper objectMapper = new ObjectMapper();

	// =============response����=================
	protected HttpServletResponse resp;

	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp = arg0;
	}

	// =============ModelDriven��֧��=============

	protected T model;

	public BaseAction() {
		try {
			// ͨ�������ȡModel����ʵ����
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazzz = (Class<T>) pt.getActualTypeArguments()[0];
			// ͨ�����䴴��Model��ʵ��
			model = clazzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============��ҳ=======================
	protected int page = 1;//��ǰҳ
	protected int rows = 20;//ÿҳ��ʾ������

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// ============�ϴ�ͼƬ===================

	/**
	 *  ͼƬ�ϴ�
	 * @param upLoad �ļ�
	 * @param path ·��
	 * @return
	 * @throws Exception
	 */
	protected List<ImgSpec> upload(UpLoad upLoad, String path) throws Exception {
		System.out.println("BaseAction upload--------------");
		//����ͼƬ�������
		List<ImgSpec> imgSpecs=new ArrayList<ImgSpec>();
		//��ϵͳʱ�������������ļ�������
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		for (int i = 0; i < upLoad.getFile().size(); ++i) {
			//����ͼƬ���
			ImgSpec imgSpec=new ImgSpec();
			InputStream is = new FileInputStream(upLoad.getFile().get(i));
			
			// ��������ͼ����
			String postfix = upLoad
					.getFileFileName()
					.get(i)
					.substring(upLoad.getFileFileName().get(i).lastIndexOf("."),
							upLoad.getFileFileName().get(i).length());
			upLoad.getFileFileName().set(i, UUID.randomUUID() + postfix);
			// ����ļ���Ŀ¼���Ƿ��д��ļ���
			File destFile = new File(path + sdf.format(date) + "/");
			if (!destFile.exists() && !destFile.isDirectory()) {
				destFile.mkdirs();
			} else {
			}
			destFile = new File(path + sdf.format(date) + "/", upLoad.getFileFileName().get(i));
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];

			int length = 0;

			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
			//תbufferedImage
			BufferedImage image = ImageIO.read(destFile);
			imgSpec.setImgWidth(image.getWidth());//��
			imgSpec.setImgHeight(image.getHeight());//��
			imgSpec.setImgPath(sdf.format(date) + "/" + upLoad.getFileFileName().get(i));//ͼƬ·��
			imgSpec.setImgSize(destFile.length()/1024);//ͼƬ��С
			imgSpec.setPostfix(postfix);
			imgSpecs.add(imgSpec);
		}
		return imgSpecs;
	}

	/**
	 * 
	 * @param imgSpecs
	 * @param orginalImg
	 * @param rootPath
	 * @return
	 * @throws Exception
	 */
	public List<ImgSpec> createThumbnailImg(List<ImgSpec> specs, String orginalImg,
			String rootPath) throws Exception {
		//��Ҫ�������ļ���ַ����С
		for (ImgSpec spec : specs) {
			if (spec.getImgWidth() != null && spec.getImgHeight() != null) {
				//ԭͼ����_��x��.��׺
				String thumbnailName= orginalImg + "_" + spec.getImgWidth() + "x" + spec.getImgHeight();
				//�ж��ļ��Ƿ����
				File file=new File(rootPath+thumbnailName);
				if(!file.exists()){//�����ڣ�����
					Thumbnails
					.of(new File(rootPath + orginalImg))
					.size(spec.getImgWidth(), spec.getImgHeight())
					.outputFormat("jpg")
					.toFile(rootPath+thumbnailName);//���
				}
				file=new File(rootPath+thumbnailName);
				spec.setImgPath(thumbnailName);//����ͼ
				spec.setImgSize(file.length()/1024);//��С
				spec.setPostfix(".jpg");//��׺
			}
		}
		return specs;
	}

	// =============Service��ʵ������=============

	@Resource
	protected ISysMMService iSysMMService;

	@Resource
	protected ISysPMService iSysPMService;

	@Resource
	protected ICommodityService iCommodityService;

	@Resource
	protected ICommodityCategoryService iCommodityCategoryService;

	@Resource
	protected ICommodityBrandsService iCommodityBrandsService;

	@Resource
	protected ICommodityPropertiesService iCommodityPropertiesService;

	@Resource
	protected IWarehouseService iWarehouseService;

	@Resource
	protected ISpSupplierService iSpSupplierService;

	@Resource
	protected ISpAotuerpPurchaseOrdersService iSpAotuerpPurchaseOrdersService;

	@Resource
	protected ISpAotuerpPurchaseStorageOrdersService iSpAotuerpPurchaseStorageOrdersService;

	@Resource
	protected ISpAotuerpTreasureService iSpAotuerpTreasureService;
	
	@Resource
	protected ISpAotuerpTreasureImgService iSpAotuerpTreasureImgService;

	@Resource
	protected ISpAotuerpTreasureTaskService iSpAotuerpTreasureTaskService;

	@Resource
	protected ISpAotuerpTreasureTaskModeService iSpAotuerpTreasureTaskModeService;

}
