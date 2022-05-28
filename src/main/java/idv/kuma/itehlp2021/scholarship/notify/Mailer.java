package idv.kuma.itehlp2021.scholarship.notify;

public interface Mailer {
    boolean send(ScholarshipResult result);

    void silentSend(ScholarshipResult result);
}
