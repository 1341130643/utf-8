package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserList {
	public List<User> SeveList();
	public List<User> SeveList(@Param("userName")String userName,
							   @Param("role")String role,
							   @Param("from")Integer currentPageNo,
							   @Param("pageSize")Integer pageSize);
	public User SeveId(String userCode);
	public int addUser(User user);
	public User SeveUid(String id);
	public int UpdateUser(User user);
	public int DelUser(List<String> ids);
}
