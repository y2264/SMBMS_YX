package cn.smbms.service.role;

import cn.smbms.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
	/**
	 * 获取全部角色信息
	 * @return
	 */
	public List<Role> getRoleList();
}
