package com.aotuspace.aotuerp.web.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysPMService;

/**
 * 
 * Title:InitListener
 * Description:
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-22 ����4:34:28
 *
 */
public class InitListener implements ServletContextListener {
	
	
	public void contextInitialized(ServletContextEvent sce) {
		//��ȡ������ص�Service����
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		ISysPMService isPmService =  (ISysPMService) ac.getBean("sysPMServiceImpl");
		
		//׼������ topPrivilegeList
		List<SpEmployeePrivilege> topPrivilegeList = isPmService.findTopERPPrivList();
		sce.getServletContext().setAttribute("erpPrivilegeList", topPrivilegeList);
		System.out.println("-------------->��׼������<--------------");
		
		/*//׼������:allPrivilegeUrls
		Collection<String> allPrivilegeUrls = isPmService.getAllPrivilegeUrls();
		sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("----------->��׼������allPrivilegeUrls<-----------------");*/
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}




}
