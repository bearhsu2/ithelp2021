package idv.kuma.itehlp2021.course;

public class Position {
    private final double longitude;
    private final double latitude;

    public Position(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
