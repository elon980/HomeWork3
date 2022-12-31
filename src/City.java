import java.util.Arrays;

public class City {
    private String cityName;
    private String district;
    private String[] street;
//O(1)
    public City(String name, String district,String[] street ){
        this.cityName = name;
        this.district=district;
        this.street = street;
    }
//O(1)
    @Override
    public String toString() {
        return "City name: " + cityName + "\n" +
                "District= " + district + "\n'" +
                "Street= " + Arrays.toString(street)+"\n"+
                "-------------"
                ;
    }
    //O(1)

    void printStreet(){
        for (int i = 0; i < this.street.length; i++){
            System.out.println(this.street[i]);
        }
    }
//O(1)

    public String getCityName() {
        return this.cityName;
    }
//O(1)

    public String getDistrict() {
        return this.district;
    }
//O(1)

    public String[] getStreet() {
        return this.street;
    }
}
