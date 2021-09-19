package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.DataAccessErrorException;
import idv.kuma.itehlp2021.student.register.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ApplyScholarshipServiceTest {

    @Test
    void all_ok() throws DataAccessErrorException, ClientSideErrorException {


        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

        ApplyScholarshipService applyScholarshipService
                = new ApplyScholarshipService(studentRepository);

        ApplicationForm applicationForm
                = new ApplicationForm(12345L, 98765L);

        applyScholarshipService.apply(applicationForm);

    }
}
