package cn.smbms.service.bill;

import cn.smbms.pojo.Bill;

import java.util.List;

public interface BillService {
	/**
	 * 通过条件获取订单列表-模糊查询-billList
	 * @param bill
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	public List<Bill> getBillList(Bill bill, int currentPageNo, int pageSize);
	
	/**
	 * 增加订单
	 * @param bill
	 * @return
	 */
	public boolean add(Bill bill);
	
	/**
	 * 通过billId获取Bill
	 * @param id
	 * @return
	 */
	public Bill getBillById(String id);
	
	/**
	 * 修改订单信息
	 * @param bill
	 * @return
	 */
	public boolean modify(Bill bill);
	
	/**
	 * 通过billId删除Bill
	 * @param delId
	 * @return
	 */
	public boolean deleteBillById(String delId);
	
	/**
	 * 根据条件查询订单表记录数
	 * @param bill
	 * @return
	 */
	public int getBillCount(Bill bill);
}
