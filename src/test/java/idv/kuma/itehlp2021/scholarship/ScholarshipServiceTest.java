package idv.kuma.itehlp2021.scholarship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScholarshipServiceTest {

    @Test
    void bachelor_full_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(
                new Transcript(
                        "Bachelor",
                        new Course("Algorithm", 70, 3),
                        new Course("Computer Internet", 80, 2),
                        new Course("Operating System", 90, 3)
                ));

        Assertions.assertEquals(10_000, actual);
    }

    @Test
    void bachelor_half_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "Bachelor",
                new Course("Algorithm", 70, 3),
                new Course("Computer Internet", 70, 2),
                new Course("Operating System", 90, 3)

        ));

        Assertions.assertEquals(5_000, actual);
    }

    @Test
    void bachelor_NO_courses() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript("Bachelor"));

        Assertions.assertEquals(0, actual);
    }

    @Test
    void bachelor_NO_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "Bachelor",
                new Course("Algorithm", 70, 3),
                new Course("Computer Internet", 70, 2),
                new Course("Operating System", 70, 3)

        ));

        Assertions.assertEquals(0, actual);
    }


    ///

    @Test
    void master_full_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(
                new Transcript(
                        "Master",
                        new Course("Algorithm", 99, 3),
                        new Course("Computer Internet", 89, 2),
                        new Course("Operating System", 99, 3)
                ));

        Assertions.assertEquals(15_000, actual);
    }

    @Test
    void master_half_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "Master",
                new Course("Algorithm", 89, 3),
                new Course("Computer Internet", 79, 2),
                new Course("Operating System", 89, 3)

        ));

        Assertions.assertEquals(7_500, actual);
    }

    @Test
    void master_NO_courses() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript("Master"));

        Assertions.assertEquals(0, actual);
    }

    @Test
    void master_NO_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "Master",
                new Course("Algorithm", 79, 3),
                new Course("Computer Internet", 79, 2),
                new Course("Operating System", 79, 3)

        ));

        Assertions.assertEquals(0, actual);
    }


    //////
    @Test
    void PhD_full_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(
                new Transcript(
                        "PhD",
                        new Course("Algorithm", 90, 3),
                        new Course("Computer Internet", 90, 2),
                        new Course("Operating System", 90, 3)
                ));

        Assertions.assertEquals(40_000, actual);
    }

    @Test
    void PhD_half_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "PhD",
                new Course("Algorithm", 80, 3),
                new Course("Computer Internet", 80, 2),
                new Course("Operating System", 80, 3)

        ));

        Assertions.assertEquals(20_000, actual);
    }

    @Test
    void PhD_NO_courses() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript("PhD"));

        Assertions.assertEquals(0, actual);
    }

    @Test
    void PhD_NO_scholarship() throws ScholarshipService.UnkownProgramTypeException {

        ScholarshipService service = new ScholarshipService();

        int actual = service.calculate(new Transcript(
                "PhD",
                new Course("Algorithm", 79, 3),
                new Course("Computer Internet", 79, 2),
                new Course("Operating System", 79, 3)

        ));

        Assertions.assertEquals(0, actual);
    }
}