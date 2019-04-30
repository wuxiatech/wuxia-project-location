package cn.wuxia.project.ad.core.service.impl;

import cn.wuxia.common.util.ListUtil;
import cn.wuxia.common.util.StringUtil;
import cn.wuxia.common.util.baidu.MapApiUtil;
import cn.wuxia.project.ad.core.dao.AddressBase2015Dao;
import cn.wuxia.project.ad.core.entity.AddressBase2015;
import cn.wuxia.project.ad.core.enums.AddressBase2015LevelEnum;
import cn.wuxia.project.ad.core.bean.SuggestBoxVo;
import cn.wuxia.project.ad.core.service.AddressBase2015Service;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.project.common.support.CacheSupport;
import cn.wuxia.project.common.support.Constants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * [ticket id] Description of the class
 *
 * @author songlin @ Version : V<Ver.No> <16 Oct, 2015>
 */
@Service
@Transactional
public class AddressBase2015ServiceImpl extends CommonServiceImpl<AddressBase2015, String> implements AddressBase2015Service {
    private String className = getClass().getSimpleName();

    @Autowired
    private AddressBase2015Dao addressBase2015Dao;

    @Override
    protected CommonDao<AddressBase2015, String> getCommonDao() {
        return addressBase2015Dao;
    }

    /**
     * key is the provinceName
     *
     * @return
     * @author songlin
     */
    @SuppressWarnings("unchecked")
    public Map<String, AddressBase2015> getProvinceMap() {
        String key = Constants.CACHED_VALUE_BASE + className + ".getProvinceMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> provinces = addressBase2015Dao.findAllProvince();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : provinces) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(key, map);
            v = CacheSupport.get(key);
        }
        return (Map<String, AddressBase2015>) v;

    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT, value = Constants.CACHED_VALUE_BASE)
    public Map<String, String> getProvinceNameMap() {
        List<AddressBase2015> list = findAllProvince();
        Map<String, String> map = Maps.newHashMap();
        for (AddressBase2015 ab : list) {
            map.put(ab.getId(), ab.getName());
        }
        return map;
    }

    public Map<String, AddressBase2015> getCityMap() {
        String key = Constants.CACHED_VALUE_BASE + className + ".getCityMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllCity();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(key, map);
            v = CacheSupport.get(key);
        }
        return (Map<String, AddressBase2015>) v;
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT, value = Constants.CACHED_VALUE_BASE)
    public Map<String, String> getCityNameMap() {
        List<AddressBase2015> citys = addressBase2015Dao.findAllCity();
        Map<String, String> map = Maps.newHashMap();
        for (AddressBase2015 ab : citys) {
            map.put(ab.getId(), ab.getName());
        }
        return map;
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#name", value = Constants.CACHED_VALUE_BASE)
    public List<AddressBase2015> findByName(String name) {
        return addressBase2015Dao.findByName(name);
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#mergerName", value = Constants.CACHED_VALUE_BASE)
    public List<AddressBase2015> findLikeFullName(String mergerName) {
        if (!StringUtil.startsWith(mergerName, "%")) {
            mergerName = "%" + mergerName;
        }
        if (!StringUtil.endsWith(mergerName, "%")) {
            mergerName = mergerName + "%";
        }
        return addressBase2015Dao.findLikeMergerName(mergerName);
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#shortName", value = Constants.CACHED_VALUE_BASE)
    public List<AddressBase2015> findLikeShortName(String shortName) {
        if (!StringUtil.startsWith(shortName, "%")) {
            shortName = "%" + shortName;
        }
        if (!StringUtil.endsWith(shortName, "%")) {
            shortName = shortName + "%";
        }
        return addressBase2015Dao.find(Restrictions.like("shortName", shortName));
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT, value = Constants.CACHED_VALUE_BASE)
    public List<AddressBase2015> findAllProvince() {
        return addressBase2015Dao.findAllProvince();
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#parentId", value = Constants.CACHED_VALUE_BASE)
    public List<AddressBase2015> findByParentId(Long parentId) {
        return addressBase2015Dao.findByParentId(parentId);
    }

    public List<AddressBase2015> getAllCityMap() {

        String key = Constants.CACHED_VALUE_BASE + className + ".getAllCityMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllCity();
            CacheSupport.set(key, citys);
            v = CacheSupport.get(key);
        }

        return (List<AddressBase2015>) v;
    }

    public Map<String, AddressBase2015> getAreaMap() {
        String key = Constants.CACHED_VALUE_BASE + className + ".getCityMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllCountry();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(key, map);
            v = CacheSupport.get(key);
        }
        return (Map<String, AddressBase2015>) v;
    }

    public List<AddressBase2015> getAllAreaMap() {

        String key = Constants.CACHED_VALUE_BASE + className + ".getAllAreaMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllCountry();
            CacheSupport.set(key, citys);
            v = CacheSupport.get(key);
        }

        return (List<AddressBase2015>) v;
    }

    public Map<String, AddressBase2015> getNationMap() {
        String key = Constants.CACHED_VALUE_BASE + className + ".getNationMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> city = addressBase2015Dao.findAllNation();
            Map<String, AddressBase2015> map = Maps.newHashMap();
            for (AddressBase2015 ab : city) {
                map.put(ab.getName(), ab);
            }
            CacheSupport.set(key, map);
            v = CacheSupport.get(key);
        }
        return (Map<String, AddressBase2015>) v;
    }

    public List<AddressBase2015> getAllNationMap() {
        String key = Constants.CACHED_VALUE_BASE + className + ".getAllNationMap";
        Object v = CacheSupport.get(key);
        if (v == null) {
            List<AddressBase2015> citys = addressBase2015Dao.findAllNation();
            CacheSupport.set(key, citys);
            v = CacheSupport.get(key);
        }

        return (List<AddressBase2015>) v;
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#ip", value = Constants.CACHED_VALUE_BASE)
    public AddressBase2015 findCityByIP(String ip) {
        if (StringUtil.equals(ip, "127.0.0.1") || StringUtil.equals(ip, "localhost") || StringUtil.startsWith(ip, "192.168.")) {
            ip = "121.32.22.161";
        }
        AddressBase2015 city = null;
        try {
//            IPLocationBan // l = IPUtil.getIPLocation(ip);
//            // if (StringUtil.isBlank(l.getCity())) {
//            l = IPUtil.getIPLocationByRemote(ip);
//            // }
//            if (StringUtil.isNotBlank(l.getCity())) {
//                List<AddressBase2015> cityList = findByName(l.getCity() + "市");
//                if (ListUtil.isNotEmpty(cityList)) {
//                    city = cityList.get(0);
//                }
//            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return city;
    }

    @Cacheable(key = Constants.CACHED_KEY_DEFAULT + "+#provinceId", value = Constants.CACHED_VALUE_BASE)
    public List<SuggestBoxVo> findCityByProvinceId(Long provinceId) {
        List<AddressBase2015> AddressBase2015 = addressBase2015Dao.findByParentId(provinceId);
        return copyToSuggestVo(AddressBase2015);
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

    public AddressBase2015 findByLngAndLat(float lng, float lat) {
        Map<String, Object> location = MapApiUtil.getAddress(lng, lat);
        String city = (String) location.get("city");
        List<AddressBase2015> citys = findByName(city);
        if (ListUtil.isNotEmpty(citys))
            return citys.get(0);
        return null;
    }


    /*
     * (non-Javadoc)
     * @see cn.zuji.fdd.core.resource.service.AddressBase2015Service#findByNameAndLevel(String, AddressBase2015LevelEnum)
     */
    @Override
    public AddressBase2015 findByNameAndLevel(String name, AddressBase2015LevelEnum addressLevel) {
        switch (addressLevel) {
            case NATION:
                return getNationMap().get(name);
            case PROVINCE:
                return getProvinceMap().get(name);
            case CITY:
                return getCityMap().get(name);
            case COUNTRY_DISTRICT:
                return getAreaMap().get(name);
            default:
                break;
        }
        return null;
    }
}
