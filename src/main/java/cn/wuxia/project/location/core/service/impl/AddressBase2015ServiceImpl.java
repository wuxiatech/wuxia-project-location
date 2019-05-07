package cn.wuxia.project.location.core.service.impl;

import cn.wuxia.common.util.ListUtil;
import cn.wuxia.common.util.StringUtil;
import cn.wuxia.common.util.baidu.MapApiUtil;
import cn.wuxia.project.common.dao.CommonDao;
import cn.wuxia.project.common.service.impl.CommonServiceImpl;
import cn.wuxia.project.common.support.CacheConstants;
import cn.wuxia.project.common.support.CacheSupport;
import cn.wuxia.project.common.third.aliyun.IpSeekerUtil;
import cn.wuxia.project.common.third.aliyun.bean.IpAdress;
import cn.wuxia.project.location.core.bean.SuggestBoxVo;
import cn.wuxia.project.location.core.dao.AddressBase2015Dao;
import cn.wuxia.project.location.core.entity.AddressBase2015;
import cn.wuxia.project.location.core.enums.AddressBase2015LevelEnum;
import cn.wuxia.project.location.core.service.AddressBase2015Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;
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
    @Override
    public Map<String, AddressBase2015> getProvinceMap() {
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

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT, value = CacheConstants.CACHED_VALUE_1_DAY)
    public Map<String, String> getProvinceNameMap() {
        List<AddressBase2015> list = findAllProvince();
        Map<String, String> map = Maps.newHashMap();
        for (AddressBase2015 ab : list) {
            map.put(ab.getId(), ab.getName());
        }
        return map;
    }

    @Override
    public Map<String, AddressBase2015> getCityMap() {
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
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT, value = CacheConstants.CACHED_VALUE_1_DAY)
    public Map<String, String> getCityNameMap() {
        List<AddressBase2015> citys = addressBase2015Dao.findAllCity();
        Map<String, String> map = Maps.newHashMap();
        for (AddressBase2015 ab : citys) {
            map.put(ab.getId(), ab.getName());
        }
        return map;
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#name", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findByName(String name) {
        return addressBase2015Dao.findByName(name);
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#mergerName", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findLikeFullName(String mergerName) {
        if (!StringUtil.startsWith(mergerName, "%")) {
            mergerName = "%" + mergerName;
        }
        if (!StringUtil.endsWith(mergerName, "%")) {
            mergerName = mergerName + "%";
        }
        return addressBase2015Dao.findLikeMergerName(mergerName);
    }

    @Override
    @Cacheable(key = CacheConstants.CACHED_KEY_DEFAULT + "+#shortName", value = CacheConstants.CACHED_VALUE_1_DAY)
    public List<AddressBase2015> findLikeShortName(String shortName) {
        if (!StringUtil.startsWith(shortName, "%")) {
            shortName = "%" + shortName;
        }
        if (!StringUtil.endsWith(shortName, "%")) {
            shortName = shortName + "%";
        }
        return addressBase2015Dao.find(Restrictions.like("shortName", shortName));
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

    @Override
    public Map<String, AddressBase2015> getAreaMap() {
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

    @Override
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

    @Override
    public Map<String, AddressBase2015> getNationMap() {
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


    /**
     * 根据ip查找归属地
     *
     * @param ip
     * @return
     */
    private IpAdress ipBelongingTo(String ip) {
        if (StringUtil.isNotBlank(ip)) {
            String[] ips = StringUtil.split(ip, ",");
            if (ArrayUtils.isNotEmpty(ips) & ips.length > 1) {
                ip = StringUtil.trimBlank(ips[1]);
            }
            /**
             * 缓存的key取前3位
             */
            String ipKey = StringUtil.substringBeforeLast(ip, ".");
            IpAdress ipAdress = IpSeekerUtil.ip2location(ip);
            if (ipAdress == null || ipAdress.isEmpty() || StringUtil.isBlank(ipAdress.getCity())) {
                ipAdress = IpSeekerUtil.getAdress(ip);
            }
            if (ipAdress == null || ipAdress.isEmpty() || StringUtil.isBlank(ipAdress.getCity())) {
                ipAdress = IpSeekerUtil.getIpAdressByLocal(ip);
            }

            return ipAdress;
        } else {
            return null;
        }
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
