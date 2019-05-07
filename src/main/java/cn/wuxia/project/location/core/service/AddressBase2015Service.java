/*
 * Created on :2015年10月10日 Author :Wind.Zhao Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.location.core.service;

import cn.wuxia.project.location.core.bean.SuggestBoxVo;
import cn.wuxia.project.location.core.entity.AddressBase2015;
import cn.wuxia.project.location.core.enums.AddressBase2015LevelEnum;
import cn.wuxia.project.common.service.CommonService;

import java.util.List;
import java.util.Map;


public interface AddressBase2015Service extends CommonService<AddressBase2015, String> {

    @Deprecated
    public Map<String, AddressBase2015> getProvinceMap();

    @Deprecated
    public Map<String, String> getProvinceNameMap();

    @Deprecated
    public Map<String, AddressBase2015> getCityMap();

    @Deprecated
    public Map<String, String> getCityNameMap();

    /**
     * 根据名字模糊查找
     * @param name
     * @return
     */
    public List<AddressBase2015> findByName(String name);

    /**
     * 根据全称模糊查找
     * @param mergerName
     * @return
     */
    @Deprecated
    public List<AddressBase2015> findLikeFullName(String mergerName);

    /**
     * 根据短名姓查找
     *
     * @author guwen
     */
    @Deprecated
    public List<AddressBase2015> findLikeShortName(String mergerName);

    /**
     * 查找所有省份
     * @return
     */
    public List<AddressBase2015> findAllProvince();

    /**
     * 根据父ID查找
     * @param parentId
     * @return
     */
    public List<AddressBase2015> findByParentId(String parentId);

    /**
     * 查找所有城市
     * @return
     */
    @Deprecated
    public List<AddressBase2015> getAllCity();

    /**
     * 查找区域，key为所在区域
     * @return
     */
    @Deprecated
    public Map<String, AddressBase2015> getAreaMap();

    /**
     * 查找所有区域
     * @return
     */
    @Deprecated
    public List<AddressBase2015> getAllArea();

    /**
     * 国家
     * @return
     */
    @Deprecated
    public Map<String, AddressBase2015> getNationMap();

    /**
     * 查找所有国家
     * @return
     */
    public List<AddressBase2015> getAllNation();

    /**
     * 根据父ID查找
     * @param parentId
     * @return
     */
    public List<SuggestBoxVo> findChildrens(String parentId);
    /**
     * 根据省份查找城市
     * @see {@link #findChildrens}
     * @param provinceId
     * @return
     */
    @Deprecated
    public List<SuggestBoxVo> findCityByProvinceId(String provinceId);

    /**
     * 根据经纬度查找所在城市
     * @param lng
     * @param lat
     * @return
     */
    public AddressBase2015 findByLngAndLat(float lng, float lat);

    /**
     *
     * @param name
     * @param addressLevel
     * @return
     */
    @Deprecated
    public AddressBase2015 findByNameAndLevel(String name, AddressBase2015LevelEnum addressLevel);
}
