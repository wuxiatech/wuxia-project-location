package cn.wuxia.project.location.core.entity;

import cn.wuxia.project.common.model.BaseUuidEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * The persistent class for the dic_address database table.
 */
@Entity
@Table(name = "dic_address_base")
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "parentAddressBase"})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AddressBase2015 extends BaseUuidEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String shortName;

    private String parentId;

    private String mergerName;

    private int level;

    private String code;

    private String zip;

    private Float lng;

    private Float lat;

    private String pinyin;

    private String simplePinyin;

    private List<AddressBase2015> childAddressBase;

    public AddressBase2015() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SHORT_NAME")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "MERGER_NAME")
    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }


    @Column(name = "LEVEL_TYPE", columnDefinition = "tinyint")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "CITY_CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "ZIP_CODE")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Column(name = "SIMPLE_PINYIN")
    public String getSimplePinyin() {
        return simplePinyin;
    }

    public void setSimplePinyin(String simplePinyin) {
        this.simplePinyin = simplePinyin;
    }

    @Transient
    public List<AddressBase2015> getChildAddressBase() {
        return childAddressBase;
    }

    public void setChildAddressBase(List<AddressBase2015> childAddressBase) {
        this.childAddressBase = childAddressBase;
    }

    @Column(name = "PARENT_ID")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
