package idv.kuma.itehlp2021.scholarship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScholarshipServiceTest {

    @Test
    void full_scholarship() {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                new Course("Algorithm", 70),
                new Course("Computer Internet", 80),
                new Course("Operating System", 90)

        ));

        Assertions.assertEquals(10_000, actual);


    }
}