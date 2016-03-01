package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

//==>구매관리 서비스 구현
@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;	
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase");		
	}

	@Override
	public Purchase getPurchaseByTranNo(int tranNo) throws Exception {
		return  sqlSession.selectOne("PurchaseMapper.getPurchaseByTranNo",tranNo);
	}

	@Override
	public Purchase getPurchaseByProdNo(int prodNo) throws Exception {
		return  sqlSession.selectOne("PurchaseMapper.getPurchaseByProdNo",prodNo);
	}

	@Override
	public List<Purchase> getPurchaseList(@Param("search") Search search, @Param("buyerId") String buyerId) throws Exception {
		Map<String, Object> purchaseMapSource = new HashMap<String, Object>();
		purchaseMapSource.put("search", search);
		purchaseMapSource.put("buyerId", buyerId);
		return sqlSession.selectList("PurchaseService.getPurchaseList", purchaseMapSource);
	}

	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {
		return sqlSession.selectList("PurchaseService.getSaleList", search);
	}

	@Override
	public void updatePurcahse(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseService.updatePurchase",purchase);		
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseService.updateTranCode",purchase);
	}
	
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseService.getTotalCount", search);
	}
}
