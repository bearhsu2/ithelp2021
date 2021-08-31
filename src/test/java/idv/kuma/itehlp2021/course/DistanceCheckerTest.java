package idv.kuma.itehlp2021.course;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DistanceCheckerTest {
    @Test
    void closed_enough() {

        DistanceCalculator calculator = Mockito.mock(DistanceCalculator.class);
        Mockito.when(calculator.calculate(1D, 1D, 0D, 0D))
                .thenReturn(49D);

        CourseRepository repository = Mockito.mock(CourseRepository.class);
        Course course = new Course(new ClassRoom(0D, 0D));

        Mockito.when(repository.find(9527L))
                .thenReturn(course);

        DistanceChecker distanceChecker = new DistanceChecker(repository, calculator);

        distanceChecker.checkDistance(9527L, 1D, 1D);

    }
}