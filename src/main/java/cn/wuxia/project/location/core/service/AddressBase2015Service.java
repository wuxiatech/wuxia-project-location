/*
 * Created on :2015年10月10日 Author :Wind.Zhao Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.location.core.service;

import cn.wuxia.project.common.service.CommonService;
import cn.wuxia.project.location.core.bean.SuggestBoxVo;
import cn.wuxia.project.location.core.entity.AddressBase2015;
import cn.wuxia.project.location.core.enums.AddressBase2015LevelEnum;

import java.util.List;


public interface AddressBase2015Service extends CommonService<AddressBase2015, String> {





    /**
     * 根据名字模糊查找
     * @param name
     * @return
     */
    public List<AddressBase2015> findByName(String name);


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
     * 更具级别模糊查找
     * @param name
     * @param addressLevel
     * @return
     */
    public List<AddressBase2015> findByNameAndLevel(String name, AddressBase2015LevelEnum addressLevel);

    /**
     * 更具市区查找区，如：白云区， 广州市
     * @param countryName
     * @param cityName
     * @return
     */
    public AddressBase2015 findByCountryNameAndCityName(String countryName, String cityName);


    /**
     * 更具市区查找区，如： 广州市, 广东省
     * @param cityName
     * @param provinceName
     * @return
     */
    public AddressBase2015 findByCityNameAndProvinceName(String cityName, String provinceName);
}
