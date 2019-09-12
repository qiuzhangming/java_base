/**
 * @Classname CountryEnum
 * @Description TODO
 * @Date 2019/9/12 11:11
 * @Created by joe
 */
public enum CountryEnum {

    ONE(1,"a"),
    TWO(2,"b"),
    THREE(3,"c"),
    FOUR(4,"d"),
    FIVE(5,"e"),
    SIX(6,"f");

    private Integer id;
    private String myName;

    CountryEnum(Integer id, String myName) {
        this.id = id;
        this.myName = myName;
    }

    public static String foreach_CountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum : values) {
            if (index == countryEnum.id) {
                return countryEnum.myName;
            }
        }
        return "";
    }
}
