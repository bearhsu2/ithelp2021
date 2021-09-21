package idv.kuma.itehlp2021.scholarship.command;

import idv.kuma.itehlp2021.scholarship.command.usecase.RepositoryAccessDataFailException;

import java.util.Optional;

public interface ScholarshipRepository {
    Scholarship find(long scholarshipId);

    Optional<Scholarship> findOptional(long scholarshipId) throws RepositoryAccessDataFailException;
}
