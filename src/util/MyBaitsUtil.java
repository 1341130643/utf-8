package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBaitsUtil {
	private static SqlSessionFactory factory;
	static{
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession createSqlSession(){
		return factory.openSession();
	}
	
	public static void closeSqlSession(SqlSession sqlSession){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
}
