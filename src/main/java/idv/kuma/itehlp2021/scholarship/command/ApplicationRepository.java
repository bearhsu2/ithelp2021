package idv.kuma.itehlp2021.scholarship.command;


import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;

public interface ApplicationRepository {
    void create(ApplicationForm application);
}
