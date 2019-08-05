package cn.wuxia.project.location.core.service.impl;

import cn.wuxia.common.util.ListUtil;
import cn.wuxia.common.util.StringUtil;
import cn.wuxia.common.util.baidu.MapApiUtil;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.project.common.support.CacheConstants;
import cn.wuxia.project.common.support.CacheSupport;
import cn.wuxia.project.location.core.bean.SuggestBoxVo;
import cn.wuxia.project.location.core.dao.AddressBase2015Dao;
import cn.wuxia.project.location.core.entity.AddressBase2015;
import cn.wuxia.project.location.core.enums.AddressBase2015LevelEnum;
import cn.wuxia.project.location.core.service.AddressBase2015Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * [ticket id] Description of the class
 *
 * @author songlin @ Version : V<Ver.No> <16 Oct, 2015>
 */
@Service
public class AddressBase2015ServiceImpl extends CommonServiceImpl<AddressBase2015, String> implements AddressBase2015Service {
    private String className = getClass().getSimpleName();

    @Autowired
    private AddressBase2015Dao addressBase2015Dao;

    @Override
    protected CommonDao<AddressBase2015, String> getCommonDao() {
        return addressBase2015Dao;
    }

    private Cache addressCache = CacheSupport.getCache(CacheConstants.CACHED_VALUE_1_DAY);

    /**
     * key is the provinceName
     *
     * @return
     * @author songlin
     */
    private Map<String, AddressBase2015> getProvinceMap() {
        String key = className + ".getProvinceMap";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> provinces = addressBase2015Dao.findAllProvince();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : provinces) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(addressCache, key, map);
            v = map;
        }
        return (Map<String, AddressBase2015>) v;

    }


    /**
     * key 为cityName
     *
     * @return
     */
    private Map<String, AddressBase2015> getCityMap() {
        String key = className + ".getCityMap";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllCity();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(addressCache, key, map);
            v = map;
        }
        return (Map<String, AddressBase2015>) v;
    }


    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#name", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findByName(String name) {
        List list = addressBase2015Dao.findByName(name);
        /**
         * 如果没有数据则模糊查找一次
         */
        if (ListUtil.isNotEmpty(list)) {
            return list;
        }
        return findLikeName(name);
    }

    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#name", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findLikeName(String name) {
        if (!StringUtil.startsWith(name, "%")) {
            name = "%" + name;
        }
        if (!StringUtil.endsWith(name, "%")) {
            name = name + "%";
        }
        return addressBase2015Dao.find(Restrictions.like("name", name));
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT, value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findAllProvince() {
        return addressBase2015Dao.findAllProvince();
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#parentId", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findByParentId(String parentId) {
        return addressBase2015Dao.findByParentId(parentId);
    }

    @Override
    public List<AddressBase2015> getAllCity() {
        String key = className + ".getAllCity";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllCity();
            CacheSupport.set(addressCache, key, citys);
            v = citys;
        }

        return (List<AddressBase2015>) v;
    }

    /**
     * key为countryName
     *
     * @return
     */
    private Map<String, AddressBase2015> getAreaMap() {
        String key = className + ".getAreaMap";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllCountry();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(addressCache, key, map);
            v = map;
        }
        return (Map<String, AddressBase2015>) v;
    }

    public List<AddressBase2015> getAllArea() {

        String key = className + ".getAllArea";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllCountry();
            CacheSupport.set(addressCache, key, citys);
            v = citys;
        }

        return (List<AddressBase2015>) v;
    }

    /**
     * key 为nationName
     *
     * @return
     */
    private Map<String, AddressBase2015> getNationMap() {
        String key = className + ".getNationMap";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllNation();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(addressCache, key, map);
            v = map;
        }
        return (Map<String, AddressBase2015>) v;
    }

    @Override
    public List<AddressBase2015> getAllNation() {
        String key = className + ".getAllNation";
        Object v = CacheSupport.get(addressCache, key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllNation();
            CacheSupport.set(addressCache, key, citys);
            v = citys;
        }

        return (List<AddressBase2015>) v;
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#parentId", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<SuggestBoxVo> findChildrens(String parentId) {
        List<AddressBase2015> addressBase2015s = addressBase2015Dao.findByParentId(parentId);
        return copyToSuggestVo(addressBase2015s);
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#provinceId", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<SuggestBoxVo> findCityByProvinceId(String provinceId) {
        List<AddressBase2015> addressBase2015s = addressBase2015Dao.findByParentId(provinceId);
        return copyToSuggestVo(addressBase2015s);
    }

    /**
     * @param address
     * @return
     * @author songlin
     */
    private List<SuggestBoxVo> copyToSuggestVo(List<AddressBase2015> address) {
        List<SuggestBoxVo> result = Lists.newArrayList();
        for (AddressBase2015 cp : address) {
            result.add(copyToSuggestVo(cp));
        }
        return result;
    }

    /**
     * @param address
     * @return
     * @author songlin
     */
    private SuggestBoxVo copyToSuggestVo(AddressBase2015 address) {
        SuggestBoxVo vo = new SuggestBoxVo();
        vo.setId(address.getId());
        vo.setName(address.getName());
        vo.setShortName(address.getShortName());
        vo.setPinyin(address.getPinyin());
        vo.setSimplePinyin(address.getSimplePinyin());
        return vo;
    }

    @Override
    public AddressBase2015 findByLngAndLat(float lng, float lat) {
        Map<String, Object> location = MapApiUtil.getAddress(lng, lat);
        String city = (String) location.get("city");
        List<AddressBase2015> citys = findByName(city);
        if (ListUtil.isNotEmpty(citys)) {
            return citys.get(0);
        }
        return null;
    }


    @Override
    public List<AddressBase2015> findByNameAndLevel(String name, AddressBase2015LevelEnum addressLevel) {
        AddressBase2015 addressBase2015 = null;
        switch (addressLevel) {
            case NATION:
                addressBase2015 = getNationMap().get(name);
                break;
            case PROVINCE:
                addressBase2015 = getProvinceMap().get(name);
                break;
            case CITY:
                addressBase2015 = getCityMap().get(name);
                break;
            case COUNTRY_DISTRICT:
                addressBase2015 = getAreaMap().get(name);
                break;
            default:
                break;
        }
        return Lists.newArrayList(addressBase2015);
    }

    @Override
    public AddressBase2015 findByCountryNameAndCityName(String countryName, String cityName) {
        List<AddressBase2015> addressBase2015s = addressBase2015Dao.findByChildrenNameAndParentName(countryName, cityName);
        return ListUtil.isNotEmpty(addressBase2015s) ? addressBase2015s.get(0) : null;
    }

    @Override
    public AddressBase2015 findByCityNameAndProvinceName(String cityName, String provinceName) {
        List<AddressBase2015> addressBase2015s = addressBase2015Dao.findByChildrenNameAndParentName(cityName, provinceName);
        return ListUtil.isNotEmpty(addressBase2015s) ? addressBase2015s.get(0) : null;
    }
}
