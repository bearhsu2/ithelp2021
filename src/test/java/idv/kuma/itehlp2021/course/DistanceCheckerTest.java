package idv.kuma.itehlp2021.course;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceCheckerTest {
    @Test
    void closed_enough() {

        DistanceChecker distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(1D, 1D, 0D, 0D, 49D));

        assertTrue(distanceChecker.checkDistance(9527L, 1D, 1D));

    }

    private CourseRepository dummy_repository(long courseId, double classroomLongitude, double classroomLatitude) {
        Course course = new Course(new ClassRoom(classroomLongitude, classroomLatitude));

        CourseRepository repository = Mockito.mock(CourseRepository.class);
        Mockito.when(repository.find(courseId))
                .thenReturn(course);
        return repository;
    }

    private DistanceCalculator dummy_calculator(double long1, double lat1, double long2, double lat2, double distance) {
        DistanceCalculator calculator = Mockito.mock(DistanceCalculator.class);
        Mockito.when(calculator.calculate(long1, lat1, long2, lat2))
                .thenReturn(distance);
        return calculator;
    }

    @Test
    void too_far() {

        DistanceChecker distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(99D, 99D, 0D, 0D, 51D));

        assertFalse(distanceChecker.checkDistance(9527L, 99D, 99D));

    }
}