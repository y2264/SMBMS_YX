package cn.smbms.dao;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderDao {
	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getProviderList(@Param("proName") String proName , @Param("proCode") String proCode) throws Exception;
	
	/**
	 * 增加供应商
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	public int add(Provider provider) throws Exception;
	
	/**
	 * 通过proId获取Provider
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Provider getProviderById( String id) throws Exception;
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteProviderById( String delId) throws Exception;
	
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public int modify( Provider provider) throws Exception;

	/**
	 * 查询供货商编号是否存在
	 */
	public int providerCodeExist(String proCode);

	/**
	 * 查询供货商是否存在订单
	 */
	public int getBillCountByProviderId(String delid);
}
