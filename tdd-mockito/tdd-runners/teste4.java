
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SuggestionChangesServiceTest {

    @Autowired
    private SuggestionChangesService suggestionChangesService;

    @MockBean
    private SuggestionUpdateAsyncService suggestionUpdateAsyncService;

    @Test
    public void whenInjectService_thenItsNotNull() {

        assertNotNull(suggestionChangesService);
    }

    @Test
    public void whenCall_sendWaitTrue() throws BusinessException {

        SuggestionVO suggestionVO = SuggestionVO.builder()
                .protocol(0L)
                .name("name")
                .email("xxx@xxx.com")
                .phone("2222-22222")
                .reason(Reason.OTHERS)
                .comment("BLA BLA")
                .build();

        when(suggestionUpdateAsyncService.singleService(suggestionVO)).thenReturn(any(RestApiFeedback.class));

        assertTrue(suggestionChangesService.send(suggestionVO));
    }

    @Test
    public void whenCall_sendWaitFalse() throws BusinessException {

        SuggestionVO suggestionVO = SuggestionVO.builder().build();

        when(suggestionUpdateAsyncService.singleService(suggestionVO)).thenThrow(BusinessException.class);

        assertFalse(suggestionChangesService.send(suggestionVO));
    }

}
