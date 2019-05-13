/*
 * Created on :2015年10月08日 Author :songlin.li Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.location.core.dao;

import cn.wuxia.project.basic.core.common.BaseCommonDao;
import cn.wuxia.project.location.core.entity.AddressBase2015;
import cn.wuxia.project.location.core.enums.AddressBase2015LevelEnum;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AddressBase2015Dao extends BaseCommonDao<AddressBase2015, String> {

    /**
     * 根据名字精确查找
     *
     * @param name
     * @return
     */
    public List<AddressBase2015> findByName(String name) {
        return findBy("name", name);
    }

    /**
     * 根据名字模糊查找
     *
     * @param mergerName
     * @return
     */
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
    @Deprecated
    public List<AddressBase2015> findAllCity() {
        return findBy("level", AddressBase2015LevelEnum.CITY.getLevel());
    }

    /**
     * 查找所有县区
     *
     * @return
     * @author songlin
     */
    @Deprecated
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
    public List<AddressBase2015> findByParentId(String parentId) {
        return findBy("parentId", parentId);
    }

    /**
     * 根据父子名称查找子记录，如：白云区，广州市； 广州市，广东省
     *
     * @param childrenName
     * @param parentName
     * @return
     */
    public List<AddressBase2015> findByChildrenNameAndParentName(String childrenName, String parentName) {
        return find("select c from AddressBase2015 c,AddressBase2015 p where c.parentId = p.id and c.name = ? and p.name = ?", childrenName, parentName);
    }


}
