package cn.smbms.dao;

import cn.smbms.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
	/**
	 * 获取全部角色信息
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleList();
}
