package idv.kuma.itehlp2021.scholarship.register;


import org.springframework.stereotype.Component;

@Component
public interface StudentRepository {
    void register(RegisterRequest request) throws DataNotFoundException;
}
