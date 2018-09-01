package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.TestDaoService;
import util.MyBaitsUtil;

import entity.Bill;
import entity.Provider;
import entity.Role;
import entity.User;

public class TestDao implements Login,UserList,RoleList{

	public User SeveUser(User user) {
		SqlSession sqlSession = null;
		User use = new User();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			use = sqlSession.getMapper(dao.Login.class).SeveUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return use;
	}
	@Override
	public List<User> SeveList() {
		SqlSession sqlSession = null;
		List<User> list = new ArrayList<User>();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			list = sqlSession.getMapper(dao.UserList.class).SeveList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return list;
	}
	
	@Override
	public List<User> SeveList(String userName,String role,Integer currentPageNo,Integer pageSize) {
		SqlSession sqlSession = null;
		List<User> list = new ArrayList<User>();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			list = sqlSession.getMapper(dao.UserList.class).SeveList(userName,role,currentPageNo,pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return list;
	}

	public User SeveId(String userCode) {
		SqlSession sqlSession = null;
		User use = new User();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			use = sqlSession.getMapper(dao.UserList.class).SeveId(userCode);
		}catch(Exception e){
			System.out.println(444);
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return use;
	}
	
	public User SeveUid(String id) {
		SqlSession sqlSession = null;
		User use = new User();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			use = sqlSession.getMapper(dao.UserList.class).SeveUid(id);
		}catch(Exception e){
			System.out.println(444);
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return use;
	}


	public List<Role> SeveRole() {
		SqlSession sqlSession = null;
		List<Role> list = new ArrayList<Role>();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			list = sqlSession.getMapper(dao.RoleList.class).SeveRole();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return list;
	}

	public int addUser(User user) {
		SqlSession sqlSession = null;
		int result = 0;
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			result = sqlSession.getMapper(dao.UserList.class).addUser(user);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			result = 0;
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return result;
	}

	public int UpdateUser(User user) {
		SqlSession sqlSession = null;
		int result = 0;
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			result = sqlSession.getMapper(dao.UserList.class).UpdateUser(user);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			result = 0;
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return result;
	}

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDaoService helloSpring = (TestDaoService) context.getBean("testDaoService") ;
		System.out.println(helloSpring.QueryProvider().size());
	}

	public int DelUser(List<String> ids) {
		SqlSession sqlSession = null;
		int result = 0;
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			result = sqlSession.getMapper(dao.UserList.class).DelUser(ids);
			//sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//sqlSession.rollback();
			result = 0;
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return result;
	}
	public List<Bill> QueryBill(String queryProductName, String queryProviderId,
			String queryIsPayment, Integer currentPageNo, Integer pageSize) {
		SqlSession sqlSession = null;
			List<Bill> list = new ArrayList<Bill>();
		try{
			sqlSession = MyBaitsUtil.createSqlSession();
			list = sqlSession.getMapper(BillList.class).QueryBill(queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
			//sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			//sqlSession.rollback();
		}finally{
			MyBaitsUtil.closeSqlSession(sqlSession);
		}
		return list;
	}
	public List<Provider> QueryProvider() {
		SqlSession sqlSession = null;
		List<Provider> list = new ArrayList<Provider>();
	try{
		sqlSession = MyBaitsUtil.createSqlSession();
		list = sqlSession.getMapper(BillList.class).QueryProvider();
		//sqlSession.commit();
	}catch(Exception e){
		e.printStackTrace();
		//sqlSession.rollback();
	}finally{
		MyBaitsUtil.closeSqlSession(sqlSession);
	}
	return list;
	}
}
