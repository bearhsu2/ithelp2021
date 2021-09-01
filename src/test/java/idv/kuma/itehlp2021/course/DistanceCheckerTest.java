package idv.kuma.itehlp2021.course;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceCheckerTest {
    @Test
    void closed_enough() {

        Position studentPosition = new Position(1D, 1D);
        Position classroomPosition = new Position(0D, 0D);

        DistanceChecker distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(49D, studentPosition, classroomPosition));

        assertTrue(distanceChecker.checkDistance(9527L, studentPosition));

    }

    private CourseRepository dummy_repository(long courseId, double classroomLongitude, double classroomLatitude) {
        Course course = new Course(new ClassRoom(classroomLongitude, classroomLatitude));

        CourseRepository repository = Mockito.mock(CourseRepository.class);
        Mockito.when(repository.find(courseId))
                .thenReturn(course);
        return repository;
    }

    private DistanceCalculator dummy_calculator(double distance, Position position1, Position position2) {
        DistanceCalculator calculator = Mockito.mock(DistanceCalculator.class);
        Mockito.when(calculator.calculate(position1, position2.getLongitude(), position2.getLatitude()))
                .thenReturn(distance);
        return calculator;
    }

    @Test
    void too_far() {
        Position studentPosition = new Position(99D, 99D);
        Position classroomPosition = new Position(0D, 0D);

        DistanceChecker distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(51D, studentPosition, classroomPosition));

        assertFalse(distanceChecker.checkDistance(9527L, studentPosition));

    }
}