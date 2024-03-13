package cn.smbms.service.provider;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 */
	public List<Provider> getProviderList(int pageNum , String proName, String proCode);
	
	/**
	 * 增加供应商
	 * @param provider
	 * @return
	 */
	public boolean add(Provider provider);
	
	/**
	 * 通过proId获取Provider
	 * @param id
	 * @return
	 */
	public Provider getProviderById(String id);
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 */
	public int deleteProviderById(String delId);
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public boolean modify(Provider provider);

	/**
	 * 查询供货商编号是否重复
	 */
	public boolean providerCodeExist(String proCode);
}
