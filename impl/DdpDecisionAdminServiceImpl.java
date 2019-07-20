import com.github.mustfun.DdpDecisionAdminDao;
import com.github.mustfun.DdpDecisionAdminPo;
import com.github.mustfun.DdpDecisionAdminService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 系统管理员 系统管理员
 *
 * @author itar
 * @email wuhandzy@gmail.com
 * @date 2019-07-20 01:51:11
 * @since jdk 1.8
 */
@Service("ddpDecisionAdminService")
public class DdpDecisionAdminServiceImpl implements DdpDecisionAdminService {
    /*<AUTOGEN--BEGIN>*/

    @Autowired
    public DdpDecisionAdminDao ddpDecisionAdminDao;


    @Override
    public Page<DdpDecisionAdminPo> selectPaged(RowBounds rowBounds) {
        return ddpDecisionAdminDao.selectPaged(rowBounds);
    }

    @Override
    public DdpDecisionAdminPo selectByPrimaryKey(String id) {
        return ddpDecisionAdminDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteByPrimaryKey(String id) {
        return ddpDecisionAdminDao.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.insert(ddpDecisionAdmin);
    }

    @Override
    public Integer insertSelective(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.insertSelective(ddpDecisionAdmin);
    }

    @Override
    public Integer insertSelectiveIgnore(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.insertSelectiveIgnore(ddpDecisionAdmin);
    }

    @Override
    public Integer updateByPrimaryKeySelective(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.updateByPrimaryKeySelective(ddpDecisionAdmin);
    }

    @Override
    public Integer updateByPrimaryKey(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.updateByPrimaryKey(ddpDecisionAdmin);
    }

    @Override
    public Integer batchInsert(List<DdpDecisionAdminPo> list) {
        return ddpDecisionAdminDao.batchInsert(list);
    }

    @Override
    public Integer batchUpdate(List<DdpDecisionAdminPo> list) {
        return ddpDecisionAdminDao.batchUpdate(list);
    }

    /**
     * 存在即更新
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsert(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.upsert(ddpDecisionAdmin);
    }

    /**
     * 存在即更新，可选择具体属性
     *
     * @param map
     * @return
     */
    @Override
    public Integer upsertSelective(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.upsertSelective(ddpDecisionAdmin);
    }

    @Override
    public List<DdpDecisionAdminPo> query(DdpDecisionAdminPo ddpDecisionAdmin) {
        return ddpDecisionAdminDao.query(ddpDecisionAdmin);
    }

    @Override
    public Long queryTotal() {
        return ddpDecisionAdminDao.queryTotal();
    }

    @Override
    public Integer deleteBatch(List<String> list) {
        return ddpDecisionAdminDao.deleteBatch(list);
    }

    /*<AUTOGEN--END>*/

}
