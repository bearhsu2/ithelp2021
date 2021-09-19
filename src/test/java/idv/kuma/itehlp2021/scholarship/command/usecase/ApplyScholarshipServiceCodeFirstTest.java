package idv.kuma.itehlp2021.scholarship.command.usecase;

import idv.kuma.itehlp2021.scholarship.command.ApplicationChecker;
import idv.kuma.itehlp2021.scholarship.command.ApplicationRepository;
import idv.kuma.itehlp2021.scholarship.command.adapter.ApplicationForm;
import idv.kuma.itehlp2021.scholarship.command.adapter.ClientSideErrorException;
import idv.kuma.itehlp2021.scholarship.command.adapter.ServerSideErrorException;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ApplyScholarshipServiceCodeFirstTest {

    @Test
    void check_ok_then_create() throws ServerSideErrorException, ClientSideErrorException {

        // 準備申請表
        ApplicationForm application = new ApplicationForm(777L, 55688L);

        // 準備假 checker
        ApplicationChecker checker = mock(ApplicationChecker.class);
        when(checker.checkTime(application)).thenReturn(true);

        // 準備假 repository
        ApplicationRepository repository = mock(ApplicationRepository.class);

        // 執行
        new ApplyScholarshipServiceCodeFirst(checker, repository).apply(application);

        // 驗證：真的有 create 一次
        verify(repository, times(1)).create(application);
    }

    @Test
    void check_NOT_ok_then_DONT_create() throws ServerSideErrorException, ClientSideErrorException {

        // 準備申請表
        ApplicationForm applicationForm = new ApplicationForm(777L, 55688L);

        // 準備假 checker
        ApplicationChecker checker = mock(ApplicationChecker.class);
        when(checker.checkTime(applicationForm)).thenReturn(false);

        // 準備假 repository
        ApplicationRepository repository = mock(ApplicationRepository.class);

        // 執行
        new ApplyScholarshipServiceCodeFirst(checker, repository).apply(applicationForm);

        // 驗證：真的完全有 create 過
        verify(repository, never()).create(applicationForm);
    }
}