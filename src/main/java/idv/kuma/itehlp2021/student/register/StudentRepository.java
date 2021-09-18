package idv.kuma.itehlp2021.student.register;


import idv.kuma.itehlp2021.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentRepository {
    void register(RegisterRequest request) throws DataNotFoundException {
        // do nothing
    }

    public Optional<Student> find(long studentId) {
        return null;
    }
}
