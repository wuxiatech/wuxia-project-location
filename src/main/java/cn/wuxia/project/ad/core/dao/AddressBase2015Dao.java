/*
 * Created on :2015年10月08日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.ad.core.dao;

import java.util.List;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.ad.core.entity.AddressBase2015;
import cn.wuxia.project.ad.core.enums.AddressBase2015LevelEnum;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class AddressBase2015Dao extends BaseCommonDao<AddressBase2015, String> {

    public List<AddressBase2015> findByName(String name) {
        return findBy("name", name);
    }

    public List<AddressBase2015> findLikeMergerName(String mergerName) {
        return find(Restrictions.like("mergerName", mergerName));
    }

    /**
     * 查找所有省份
     *
     * @return
     * @author songlin
     */
    public List<AddressBase2015> findAllProvince() {
        return findBy("level", AddressBase2015LevelEnum.PROVINCE.getLevel());
    }

    /**
     * 查找所有城市
     *
     * @return
     * @author songlin
     */
    public List<AddressBase2015> findAllCity() {
        return findBy("level", AddressBase2015LevelEnum.CITY.getLevel());
    }

    /**
     * 查找所有县区
     *
     * @return
     * @author songlin
     */
    public List<AddressBase2015> findAllCountry() {
        return findBy("level", AddressBase2015LevelEnum.COUNTRY_DISTRICT.getLevel());
    }

    /**
     * 查找所有国家
     *
     * @return
     * @author songlin
     */
    public List<AddressBase2015> findAllNation() {
        return findBy("level", AddressBase2015LevelEnum.NATION.getLevel());
    }

    /**
     * 根据父ID查找子对象
     *
     * @param parentId
     * @return
     * @author songlin
     */
    public List<AddressBase2015> findByParentId(Long parentId) {
        return findBy("parentId", parentId);
    }


}
