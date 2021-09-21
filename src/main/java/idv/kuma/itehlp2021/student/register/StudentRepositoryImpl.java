package idv.kuma.itehlp2021.student.register;


import idv.kuma.itehlp2021.scholarship.command.usecase.RepositoryAccessDataFailException;
import idv.kuma.itehlp2021.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public void register(RegisterRequest request) throws DataNotFoundException {
        // do nothing
    }

    @Override
    public Optional<Student> find(long studentId) throws RepositoryAccessDataFailException {
        return null;
    }
}
