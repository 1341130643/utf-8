package dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Bill;
import entity.Provider;


public interface BillList {

	public List<Bill> QueryBill(@Param("queryProductName")String queryProductName,
			   @Param("queryProviderId")String queryProviderId,
			   @Param("queryIsPayment")String queryIsPayment,
			   @Param("from")Integer currentPageNo,
			   @Param("pageSize")Integer pageSize);
	
	public List<Provider> QueryProvider();
}
