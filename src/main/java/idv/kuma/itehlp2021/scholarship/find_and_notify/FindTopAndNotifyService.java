package idv.kuma.itehlp2021.scholarship.find_and_notify;

import idv.kuma.itehlp2021.scholarship.Transcript;
import idv.kuma.itehlp2021.scholarship.notify.SendResultEmailService;

import java.util.List;

public class FindTopAndNotifyService {

    private TranscriptRepository repository;
    private SendResultEmailService emailService;

    public FindTopAndNotifyService(TranscriptRepository repository, SendResultEmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void execute(String semester, long courseId) {

        List<Transcript> transcripts = repository.findHighestScore(semester, courseId);

        for (Transcript transcript : transcripts) {

            long studentId = transcript.getStudentId();

            this.emailService.send(studentId, "Congratulations! You've got Scholarship");

        }

    }
}
