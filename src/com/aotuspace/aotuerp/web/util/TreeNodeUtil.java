package com.aotuspace.aotuerp.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aotuspace.aotuerp.web.model.EasyTreeData;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:TreeNodeUtil
 * Description:���ݹ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-21 ����11:28:48
 *
 */
public class TreeNodeUtil {
	
	/**
	 * ���ݹ�
	 * @param erpPrivList ����
	 * @param layer ����
	 * @return
	 */
	public static  List<EasyTreeData> getTreeNode(Collection<SpEmployeePrivilege> erpPrivList,Integer layer) {
		List<EasyTreeData> treeNodeList = new ArrayList<EasyTreeData>();
		for (SpEmployeePrivilege erpPriv : erpPrivList) {//����
			if(layer>0){
				//System.out.println(prefix+erpPriv.getSpEpname());
				EasyTreeData treeNode = new EasyTreeData(erpPriv.getSpId(),erpPriv.getSpEpname(),erpPriv.getSpEpurl(), erpPriv.getSpState(),
						erpPriv.getSpIconcls());
				treeNode.setChildren(getTreeNode(erpPriv.getSpEpchildren(),layer-1));
				
				if(treeNode.getChildren().size()==0){
					treeNode.setChildren(null);
				}
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	
	
}
