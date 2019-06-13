package countdownlock;

public enum CityEnum {

    CG(1, "楚国"),
    YG(2, "燕国"),
    WG(3, "魏国"),
    QG(4, "齐国"),
    ZG(5, "赵国"),
    HG(6, "韩国");

    private Integer code;
    private String name;

    CityEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CityEnum getNameByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (CityEnum value : CityEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
