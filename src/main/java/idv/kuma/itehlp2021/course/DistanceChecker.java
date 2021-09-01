package idv.kuma.itehlp2021.course;


public class DistanceChecker {

    private final CourseRepository courseRepository;
    private final DistanceCalculator distanceCalculator;

    public DistanceChecker(CourseRepository courseRepository, DistanceCalculator distanceCalculator) {
        this.courseRepository = courseRepository;
        this.distanceCalculator = distanceCalculator;
    }

    public boolean checkDistance(long courseId, Position studentPosition) {

        Position classroomPosition = courseRepository.find(courseId).getClassRoom().getPosition();

        double distance = distanceCalculator.calculate(studentPosition, classroomPosition);

        return distance < 50D;
    }

}
