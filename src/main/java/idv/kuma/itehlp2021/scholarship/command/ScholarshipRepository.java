package idv.kuma.itehlp2021.scholarship.command;

import idv.kuma.itehlp2021.scholarship.command.usecase.RepositoryAccessDataFailException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScholarshipRepository {
    public Scholarship find(long scholarshipId) {
        return null;
    }

    public Optional<Scholarship> findOptional(long scholarshipId) throws RepositoryAccessDataFailException {
        return null;
    }
}
