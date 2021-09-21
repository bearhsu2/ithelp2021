package idv.kuma.itehlp2021.scholarship.command;

import idv.kuma.itehlp2021.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Scholarship {
    private LocalDate deadline;

    public boolean isQualified(Student student) {
        return student.getProgram().equals("PhD");
    }

    public boolean isOvertime() {
        LocalDate deadline = getDeadline();
        LocalDate now = LocalDate.now();
        boolean isOverTime = now.isAfter(deadline);
        return isOverTime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
