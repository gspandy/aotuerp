//��չeasyui������֤
$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					//��֤����  
					CHS : {
						validator : function(value) {
							return /^[\u0391-\uFFE5]+$/.test(value);
						},
						message : '�����뺺��'
					},
					//�ƶ��ֻ�������֤    
					Mobile : {//valueֵΪ�ı����е�ֵ  
						validator : function(value) {
							var reg = /^1[3|4|5|8|9]\d{9}$/;
							return reg.test(value);
						},
						message : '��������Ч���ֻ�����'
					},
					//�����ʱ���֤   
					ZipCode : {
						validator : function(value) {
							var reg = /^[0-9]\d{5}$/;
							return reg.test(value);
						},
						message : 'The zip code must be 6 digits and 0 began.'
					},
					//����
					Number : {
						validator : function(value) {
							var reg = /^[0-9]*$/;
							return reg.test(value);
						},
						message : '����������'
					},
					IdCard : {
						validator : function(value) {
							var reg = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
							return reg.test(value);
						},
						message : '��������Ч�����֤����'
					},
					//���п�
					BankId : {
						validator : function(value) {
							return luhmCheck(value);
						},
						message : '��������Ч���п�����'
					}
				})

//Create Time:  07/28/2011
//Operator:     liuzw
//Description:  ���п���LuhmУ��

//LuhmУ�����16λ���п��ţ�19λͨ�ã�:

// 1.��δ��У��λ�� 15����18��λ���Ŵ������α�� 1 �� 15��18����λ������λ���ϵ����ֳ��� 2��
// 2.����λ�˻��ĸ�ʮλȫ����ӣ��ټ�������ż��λ�ϵ����֡�
// 3.���ӷ��ͼ���У��λ�ܱ� 10 ������
function luhmCheck(bankno) {
	var lastNum = bankno.substr(bankno.length - 1, 1);//ȡ�����һλ����luhm���бȽϣ�

	var first15Num = bankno.substr(0, bankno.length - 1);//ǰ15��18λ
	var newArr = new Array();
	for ( var i = first15Num.length - 1; i > -1; i--) { //ǰ15��18λ����������
		newArr.push(first15Num.substr(i, 1));
	}
	var arrJiShu = new Array(); //����λ*2�Ļ� <9
	var arrJiShu2 = new Array(); //����λ*2�Ļ� >9

	var arrOuShu = new Array(); //ż��λ����
	for ( var j = 0; j < newArr.length; j++) {
		if ((j + 1) % 2 == 1) {//����λ
			if (parseInt(newArr[j]) * 2 < 9)
				arrJiShu.push(parseInt(newArr[j]) * 2);
			else
				arrJiShu2.push(parseInt(newArr[j]) * 2);
		} else
			//ż��λ
			arrOuShu.push(newArr[j]);
	}

	var jishu_child1 = new Array();//����λ*2 >9 �ķָ�֮��������λ��
	var jishu_child2 = new Array();//����λ*2 >9 �ķָ�֮�������ʮλ��
	for ( var h = 0; h < arrJiShu2.length; h++) {
		jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
		jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
	}

	var sumJiShu = 0; //����λ*2 < 9 ������֮��
	var sumOuShu = 0; //ż��λ����֮��
	var sumJiShuChild1 = 0; //����λ*2 >9 �ķָ�֮��������λ��֮��
	var sumJiShuChild2 = 0; //����λ*2 >9 �ķָ�֮�������ʮλ��֮��
	var sumTotal = 0;
	for ( var m = 0; m < arrJiShu.length; m++) {
		sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
	}

	for ( var n = 0; n < arrOuShu.length; n++) {
		sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
	}

	for ( var p = 0; p < jishu_child1.length; p++) {
		sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
		sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
	}
	//�����ܺ�
	sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
			+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

	//����Luhmֵ
	var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
	var luhm = 10 - k;

	if (lastNum == luhm) {
		return true;
	} else {
		return false;
	}
}