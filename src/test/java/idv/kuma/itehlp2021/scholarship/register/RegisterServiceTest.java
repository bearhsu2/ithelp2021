package idv.kuma.itehlp2021.scholarship.register;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Fail.fail;

class RegisterServiceTest {

    @Test
    void when_student_not_exists() throws DataNotFoundException {

        StudentRepository repository = Mockito.mock(StudentRepository.class);
        Mockito.doThrow(new DataNotFoundException())
                .when(repository).register(request(35L));

        RegisterService service = new RegisterService(repository);

        try {
            service.execute(request(35L));
            fail("should throw exception");
        } catch (StudentNotExistException e) {
            Assertions.assertThat(e).hasMessageContaining("not exists");
        }


    }

    private RegisterRequest request(long studentId) {
        return new RegisterRequest(studentId);
    }
}