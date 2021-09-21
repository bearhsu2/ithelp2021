package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.entity.Scholarship;

import java.util.Optional;

public interface ScholarshipRepository {
    Scholarship find(long scholarshipId);

    Optional<Scholarship> findOptional(long scholarshipId) throws RepositoryAccessDataFailException;
}
