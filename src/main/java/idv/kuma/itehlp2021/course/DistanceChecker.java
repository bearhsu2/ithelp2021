package idv.kuma.itehlp2021.course;


public class DistanceChecker {

    private CourseRepository courseRepository;
    private DistanceCalculator distanceCalculator;

    public DistanceChecker(CourseRepository courseRepository, DistanceCalculator distanceCalculator) {
        this.courseRepository = courseRepository;
        this.distanceCalculator = distanceCalculator;
    }

    public boolean checkDistance(long courseId, double longitude, double latitude) {

        Course course = courseRepository.find(courseId);

        ClassRoom classRoom = course.getClassRoom();

        double classRoomLongitude = classRoom.getLongitude();
        double classRoomLatitude = classRoom.getLatitude();

        double distance = distanceCalculator.calculate(longitude, latitude, classRoomLongitude, classRoomLatitude);

        return distance < 50D;
    }

}
