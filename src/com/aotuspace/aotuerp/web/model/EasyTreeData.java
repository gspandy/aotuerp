package com.aotuspace.aotuerp.web.model;

import java.util.ArrayList;
import java.util.List;

public class EasyTreeData {
	private Integer id;
	private Integer pid;
	private String text;//�ı�
	private String state;//'open','close' openΪ���ӽڵ㣬closeΪû���ӽڵ�
	private String iconCls;
	private String url;
	private List<EasyTreeData> children;
	
	//���캯��
	public EasyTreeData(){
		this.children=new ArrayList<EasyTreeData>();
		this.state="open";
	}
	//���ù��캯��
	public EasyTreeData(Integer id,String text,String url,String state,String iconCls){
		this.id=id;
		this.text=text;
		this.url=url;
		//this.state=state;
		this.iconCls=iconCls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<EasyTreeData> getChildren() {
		return children;
	}

	public void setChildren(List<EasyTreeData> children) {
		this.children = children;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
