package idv.kuma.itehlp2021.student.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

    private StudentRepository repository;

    @Autowired
    public RegisterService(StudentRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterRequest request) throws StudentNotExistException {
        try {
            repository.register(request);
        } catch (DataNotFoundException e) {
            throw new StudentNotExistException("Student not exists", e);
        }

    }
}

