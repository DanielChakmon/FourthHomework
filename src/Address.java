public class Address {
    private String cityName;
    private String streetName;

    public Address(String cityName, String streetName) {
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String toString() {
        return "cityName: " + cityName + ", streetName: " + streetName;
    }
}
