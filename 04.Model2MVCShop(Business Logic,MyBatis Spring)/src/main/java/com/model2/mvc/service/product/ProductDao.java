package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

//==> ��ǰ�������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface ProductDao {
	
	//��ǰ�߰�
	public void insertProduct(Product product) throws Exception;
	
	//��ǰ������������
	public Product findProduct(int productNo) throws Exception;

	//��ǰ����Ʈ��������
	public Map<String,Object> getProductList(Search search) throws Exception;

	//��ǰ��������
	public void updateProduct(Product product) throws Exception;
	
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
}