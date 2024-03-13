package cn.smbms.dao;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
	/**
	 * 登录
	 * 
	 * @param userCode
	 * @return
	 */
	public User login(@Param("userCode") String userCode) throws Exception;

	/**
	 * 修改当前用户密码
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(@Param("id") int id,@Param("pwd") String pwd) throws Exception;

	/**
	 * 通过条件查询-userList
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(@Param("userName") String userName,@Param("userRole") int userRole)
			throws Exception;

	/**
	 * 通过条件查询-用户表记录数
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int getUserCount(@Param("userName") String userName,@Param("userRole") int userRole) throws Exception;
	
	/**
	 * 增加用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(User user) throws Exception;
	
	/**
	 * 通过userId获取user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(@Param("id") String id) throws Exception;
	
	/**
	 * 通过userId删除user
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteUserById(@Param("delId") Integer delId) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modify(User user) throws Exception;

	/**
	 * 批量删除用户信息
	 * @param strings --存放主键id值的数组
	 */
	public int deleteByIds(@Param("strings") String[] strings);
}
