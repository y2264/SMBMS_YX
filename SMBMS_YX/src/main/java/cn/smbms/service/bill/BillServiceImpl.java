package cn.smbms.service.bill;

import cn.smbms.dao.BillDao;
import cn.smbms.pojo.Bill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillDao billDao;

	@Override
	public List<Bill> getBillList(Bill bill, int currentPageNo, int pageSize) {
		List<Bill> billList = null;
		try {
			billList = billDao.getBillList(bill, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billList;
	}

	@Override
	public boolean add(Bill bill) {
		boolean flag = false; // 创建是否添加成功的变量，初始值为false
		try {
			if (billDao.add(bill) > 0) // 如果添加方法操作影响的行数大于0--说明添加成功
				flag = true; // 将是否添加成功的变量的值改为true
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return flag; // 返回添加结果
	}

	@Override
	public Bill getBillById(String id) {
		Bill bill = null; // 创建Bill对象
		try {
			bill = billDao.getBillById(id);
		} catch (Exception e) {
			e.printStackTrace();
			bill = null;
		}
		return bill;
	}

	@Override
	public boolean modify(Bill bill) {
		boolean flag = false; // 创建是否修改成功的变量，初始值为false
		try {
			// 如果修改方法操作影响的行数大于0--说明修改成功
			if (billDao.modify( bill) > 0)
				flag = true; // 将是否修改成功的变量的值改为true
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag; // 返回修改结果
	}

	@Override
	public boolean deleteBillById(String delId) {
		boolean flag = false; // 创建是否删除成功的变量，初始值为false
		try {
			// 如果删除方法操作影响的行数大于0--说明修改成功
			if (billDao.deleteBillById( delId) > 0)
				flag = true; // 将是否删除成功的变量的值改为true
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag; // 返回删除结果
	}

	@Override
	public int getBillCount(Bill bill) {
		int count = 0; // 定义总数量变量
		try {
			count = billDao.getBillCount( bill); // 调用方法并获取结果
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count; // 返回结果
	}
}
