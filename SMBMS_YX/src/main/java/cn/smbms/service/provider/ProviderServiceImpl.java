package cn.smbms.service.provider;

import cn.smbms.dao.ProviderDao;
import cn.smbms.pojo.Provider;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderDao providerDao;

    /**
     * 将供应商数据返回
     *
     * */

    @Override
    public List<Provider> getProviderList(int pageNum , String proName, String proCode) {
        List<Provider> providerList = null;
        try {
            PageHelper.startPage(pageNum , 8);
            providerList = providerDao.getProviderList(proName, proCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerList;
    }

    @Override
    public boolean add(Provider provider) {
        boolean flag = false;
        try {
            if (providerDao.add(provider) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Provider getProviderById(String id) {
        Provider provider = null;
        try {
            provider = providerDao.getProviderById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    /**
     * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作 若订单表中无该供应商的订单数据，则可以删除 若有该供应商的订单数据，则不可以删除
     * 返回值billCount 1> billCount == 0 删除---1 成功 （0） 2 不成功 （-1） 2> billCount > 0 不能删除
     * 查询成功（0）查询不成功（-1）
     * <p>
     * ---判断 如果billCount = -1 失败 若billCount >= 0 成功
     */
    @Override
    public int deleteProviderById(String delId) {
        int billCount = -1;
        try {
            billCount =  providerDao.getBillCountByProviderId(delId);
            if(billCount == 0){
                providerDao.deleteProviderById(delId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billCount;
    }

    @Override
    public boolean modify(Provider provider) {
        boolean result = false;
        try {
            System.out.println(provider.getModifyDate());
            if (providerDao.modify(provider) > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询供货商编号是否存在
     * @param proCode
     * @return
     */
    @Override
    public boolean providerCodeExist(String proCode) {
        Boolean result = false;
        if(providerDao.providerCodeExist(proCode) == 0){
            result = true;
        }
        return result;
    }

}
