package idv.kuma.itehlp2021.scholarship.notify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SendResultEmailServiceTest {
    @Test
    void when_send_returns_future() throws ExecutionException, InterruptedException {

        // 準備假 Mailer
        Mailer mailer = Mockito.mock(Mailer.class);
        SendResultEmailService service = new SendResultEmailService(mailer);

        // 假 Mailer 會回傳兩個 true，一個 false
        when(mailer.send(any(ScholarshipResult.class)))
                .thenReturn(true, true, false);

        // 跑起來
        List<Future<Boolean>> futures = service.send(
                Arrays.asList(
                        new ScholarshipResult(),
                        new ScholarshipResult(),
                        new ScholarshipResult()
                ));

        // 檢查 Future 裡 true 與 false 的個數
        int goods = 0;
        int bads = 0;
        for (Future<Boolean> future : futures) {
            if (future.get()) {
                goods++;
            } else {
                bads++;
            }
        }
        assertEquals(2, goods);
        assertEquals(1, bads);

    }
}