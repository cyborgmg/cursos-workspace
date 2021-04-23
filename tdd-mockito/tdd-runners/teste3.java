
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(OmbudsmanController.class)
class OmbudsmanControllerTest {

    @MockBean
    private ProtocolService protocolService;

    @MockBean
    private SuggestionChangesService suggestionChangesService;

    @Autowired
    private OmbudsmanController ombudsmanController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void injectControllerTest() {
        assertNotNull(ombudsmanController);
    }

    @Test
    public void whenCall_sendReturnOk() throws BusinessException {

        when(protocolService.protocolExist(any(Long.class))).thenReturn(true);

        when(suggestionChangesService.send(any(SuggestionVO.class))).thenReturn(true);

        ResponseEntity<String> responseEntity = ombudsmanController.send(SuggestionVO.builder().protocol(0L).build());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK );
    }
	
	    @Test
    public void whenCall_sendReturnBAD_REQUEST() throws BusinessException {

        when(protocolService.protocolExist(any(Long.class))).thenReturn(false);

        when(suggestionChangesService.send(any(SuggestionVO.class))).thenReturn(false);

        ResponseEntity<String> responseEntity = ombudsmanController.send(SuggestionVO.builder().protocol(0L).build());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST );
    }

    @Test
    public void whenCallPost_sendReturnOk() throws Exception {

        SuggestionVO suggestionVO = SuggestionVO.builder()
                                    .protocol(0L)
                                    .name("name")
                                    .email("xxx@xxx.com")
                                    .phone("2222-22222")
                                    .reason(Reason.OTHERS)
                                    .comment("BLA BLA").build();

        BDDMockito.given(protocolService.protocolExist(any(Long.class))).willReturn(true);
        BDDMockito.given(suggestionChangesService.send(any(SuggestionVO.class))).willReturn(true);

        mvc.perform(
                post("/ombudsman/cadastre")
                        .content(mapper.writeValueAsString(suggestionVO))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    public void whenCallPost_sendReturnBadRequest() throws Exception {

        SuggestionVO suggestionVO = SuggestionVO.builder()
                .protocol(0L)
                .name("name")
                .email("xxx@xxx.com")
                .phone("2222-22222")
                .reason(Reason.OTHERS)
                .comment("BLA BLA").build();

        BDDMockito.given(protocolService.protocolExist(any(Long.class))).willReturn(false);
        BDDMockito.given(suggestionChangesService.send(any(SuggestionVO.class))).willReturn(false);

        mvc.perform(
                post("/ombudsman/cadastre")
                        .content(mapper.writeValueAsString(suggestionVO))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenCallPost_send_whenCall_suggestionChangesServiceSendReturnBadRequest() throws Exception {

        SuggestionVO suggestionVO = SuggestionVO.builder()
                .protocol(0L)
                .name("name")
                .email("xxx@xxx.com")
                .phone("2222-22222")
                .reason(Reason.OTHERS)
                .comment("BLA BLA").build();

        BDDMockito.given(protocolService.protocolExist(any(Long.class))).willReturn(true);
        BDDMockito.given(suggestionChangesService.send(any(SuggestionVO.class))).willReturn(false);

        mvc.perform(
                post("/ombudsman/cadastre")
                        .content(mapper.writeValueAsString(suggestionVO))
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());

    }


}
