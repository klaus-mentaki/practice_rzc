public class AirportFile {
    private String airport;
    private String gender;
    private String longitude;
    private String latitude;

    /**
     * disposeCSVFile3类中创建的对象及构造方法
     */

    public AirportFile() {

    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "AirportFile{" +
                "airport='" + airport + '\'' +
                ", gender='" + gender + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public AirportFile(String airport, String gender, String longitude, String latitude) {
        super();
        this.airport = airport;
        this.gender = gender;
        this.longitude = longitude;
        this.latitude = latitude;
    }


}
