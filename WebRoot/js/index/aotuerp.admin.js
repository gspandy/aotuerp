$(function(){
	//���� ���ݿ� id��ʶ text���� state״̬ iconClsͼ�� URL��ַ nid���ڵ㣬���ݿ����������תΪjson����
	$('#nav').accordion({
		fit:true,
		border:false,
		animate:true,
		plain:true
	});
	
	$.ajax({
	    url: 'aotuerp_nav.action',
	    success: function (data) {
	       $.each(data, function (i, n) {
	            $('#nav').accordion('add', {
	                title: n.text,
	                //click:GetSmallMenu(n.MenuID,n.MenuName),
	               // iconCls: 'icon-menu-' + n.MenuImg.replace(new RegExp('.png'), ''),
	                selected: false,
	                content: '<div style="padding:10px;" id="navTree' + n.id + '"></div>',
	            });
	        
	            $('#navTree'+n.id).tree({
	            	data:n.children,
	            	onLoadSuccess : function(node,data){
	        			if(data){
	        				$(data).each(function(index, value) {
	        					//console.log(value);
	        					if(this.state=='closed'){
	        						$('#navTree'+n.id).tree('expandAll');
	        					}
	        				});
	        			}
	        		},
	        		onClick : function(node){
	        			if(node.url){
	        				if($('#tabs').tabs('exists',node.text)){
	        					$('#tabs').tabs('select',node.text);
	        				}else{
	        					$('#tabs').tabs('add',{
	        						title : node.text,
	        						closable : true,
	        						href : node.url+'.action',
	        						//����������ͼ��
	        						queryParams:{
	        							twoNav:node.id,
	        						}
	        					});
	        				}
	        			}
	        		}
	            });
	        });
	    }
	});   
	
	//ѡ�
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
});