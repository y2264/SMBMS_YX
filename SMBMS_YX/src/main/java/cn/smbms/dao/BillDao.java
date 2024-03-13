package cn.smbms.dao;

import cn.smbms.pojo.Bill;

import java.util.List;

public interface BillDao {
	/**
	 * 通过条件查询-订单表记录数
	 * @returns
	 * @throws Exception
	 */
	public int getBillCount(Bill bill) throws Exception;
	
	/**
	 * 通过查询条件获取供应商列表-模糊查询-getBillList
	 * @param bill
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(Bill bill, int currentPageNo, int pageSize) throws Exception;
	
	/**
	 * 增加订单
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public int add(Bill bill) throws Exception;
	
	/**
	 * 通过billId获取Bill
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(String id) throws Exception;
	
	/**
	 * 修改订单信息
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public int modify(Bill bill) throws Exception;
	
	/**
	 * 通过delId删除Bill
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteBillById(String delId) throws Exception;
	
	/**
	 * 根据供应商ID查询订单数量
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public int getBillCountByProviderId(String providerId) throws Exception;
}
