package cn.smbms.service.role;

import cn.smbms.dao.RoleDao;
import cn.smbms.pojo.Role;
import cn.smbms.tools.ApplicationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	public RoleServiceImpl() {}

	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = null;
		try {
			roleList = ApplicationUtil.getContext().getBean(RoleDao.class).getRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}

}
