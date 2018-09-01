package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.TestDao;

import util.MyBaitsUtil;

import entity.Bill;
import entity.Provider;
import entity.Role;
import entity.User;

public class TestDaoService{
	private TestDao testDao =new TestDao();
	public User SeveUser(User user) {
		return testDao.SeveUser(user);
	}
	public List<User> SeveList() {
		return testDao.SeveList();
	}
	public List<User> SeveList(String userName,String role,Integer currentPageNo,Integer pageSize){
		return testDao.SeveList(userName,role,currentPageNo,pageSize);
	}
	public User SeveId(String userCode){
		return testDao.SeveId(userCode);
	}

	public List<Role> SeveRole() {
		return testDao.SeveRole();
	}

	public int addUser(User user) {
		return testDao.addUser(user);
	}

	public User SeveUid(String id) {
		return testDao.SeveUid(id);
	}

	public int UpdateUser(User user) {
		return testDao.UpdateUser(user);
	}

	public int DelUser(List<String> ids){
		return testDao.DelUser(ids);
	}
	public TestDao getTestDao() {
		return testDao;
	}
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	public List<Bill> QueryBill(String queryProductName, String queryProviderId,
			String queryIsPayment, Integer currentPageNo, Integer pageSize){
		return testDao .QueryBill(queryProductName, queryProviderId,
				queryIsPayment, currentPageNo, pageSize);
	}
	public List<Provider> QueryProvider(){
		return testDao.QueryProvider();
	}
}
