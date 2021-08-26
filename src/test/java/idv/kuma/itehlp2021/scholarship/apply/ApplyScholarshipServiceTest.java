package idv.kuma.itehlp2021.scholarship.apply;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ApplyScholarshipServiceTest {

    @Test
    void check_ok_then_create() {

        // 準備申請表
        Application application = new Application(777L);

        // 準備假 checker
        CheckApplicationService checker = mock(CheckApplicationService.class);
        when(checker.checkTime(application)).thenReturn(true);

        // 準備假 repository
        ApplicationRepository repository = mock(ApplicationRepository.class);

        // 執行
        new ApplyScholarshipService(checker, repository).apply(application);

        // 驗證：真的有 create 一次
        verify(repository, times(1)).create(application);
    }
    @Test
    void check_NOT_ok_then_DONT_create() {

        // 準備申請表
        Application application = new Application(777L);

        // 準備假 checker
        CheckApplicationService checker = mock(CheckApplicationService.class);
        when(checker.checkTime(application)).thenReturn(false);

        // 準備假 repository
        ApplicationRepository repository = mock(ApplicationRepository.class);

        // 執行
        new ApplyScholarshipService(checker, repository).apply(application);

        // 驗證：真的完全有 create 過
        verify(repository, never()).create(application);
    }
}