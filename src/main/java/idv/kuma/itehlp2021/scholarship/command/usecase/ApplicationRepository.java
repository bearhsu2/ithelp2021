package idv.kuma.itehlp2021.scholarship.command.usecase;


import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.entity.Application;

public interface ApplicationRepository {
    void create(ApplicationForm application);

    void create(Application application) throws RepositoryAccessDataFailException;
}
