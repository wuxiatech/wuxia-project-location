/*
 * Created on :2015年10月10日 Author :Wind.Zhao Change History Version Date Author
 * Reason <Ver.No> <date> <who modify> <reason>
 */
package cn.wuxia.project.ad.core.service;

import cn.wuxia.project.ad.core.bean.SuggestBoxVo;
import cn.wuxia.project.ad.core.entity.AddressBase2015;
import cn.wuxia.project.ad.core.enums.AddressBase2015LevelEnum;
import cn.wuxia.project.common.service.CommonService;

import java.util.List;
import java.util.Map;


public interface AddressBase2015Service extends CommonService<AddressBase2015, String> {

    public Map<String, AddressBase2015> getProvinceMap();

    public Map<String, String> getProvinceNameMap();

    public Map<String, AddressBase2015> getCityMap();

    public Map<String, String> getCityNameMap();

    public List<AddressBase2015> findByName(String name);

    public List<AddressBase2015> findLikeFullName(String mergerName);

    /**
     * 根据短名姓查找
     *
     * @author guwen
     */
    public List<AddressBase2015> findLikeShortName(String mergerName);

    public List<AddressBase2015> findAllProvince();

    public List<AddressBase2015> findByParentId(Long parentId);

    public List<AddressBase2015> getAllCityMap();

    public Map<String, AddressBase2015> getAreaMap();

    public List<AddressBase2015> getAllAreaMap();

    public Map<String, AddressBase2015> getNationMap();

    public List<AddressBase2015> getAllNationMap();

    public AddressBase2015 findCityByIP(String ip);

    public List<SuggestBoxVo> findCityByProvinceId(Long provinceId);

    public AddressBase2015 findByLngAndLat(float lng, float lat);

    public AddressBase2015 findByNameAndLevel(String name, AddressBase2015LevelEnum addressLevel);
}
