import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {
                GenericDataController.class,
                GenericDataChangesService.class
        })
@AutoConfigureJson
@AutoConfigureWebMvc
@AutoConfigureMockMvc(addFilters =false)
@ComponentScan("br.com.xxxxxxxxxx")
class GenericDataControllerTest {

    @MockBean
    private GenericDataChangesService genericDataChangesService;

    @Autowired
    private GenericDataController genericDataController;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void injectControllerTest() {
        assertNotNull(genericDataController);
    }

    @Test
    public void whenCall_sendReturnOk() throws BusinessException {

        when(genericDataChangesService.send(any(GenericDataDTO.class))).thenReturn(true);

        ResponseEntity<String> responseEntity = genericDataController.send(GenericDataDTO.builder().build());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void whenCall_sendReturnBAD_REQUEST() throws BusinessException {

        when(genericDataChangesService.send(any(GenericDataDTO.class))).thenReturn(false);

        ResponseEntity<String> responseEntity = genericDataController.send(GenericDataDTO.builder().build());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenCallPost_sendReturnOk() throws Exception {

        GenericDataDTO genericDataDTO = GenericDataDTO.builder()
                .policyId(1)
                .billToDate(true)
                .build();

        BDDMockito.given(genericDataChangesService.send(any(GenericDataDTO.class))).willReturn(true);

        mvc.perform(
                post("/policy/change")
                        .content(mapper.writeValueAsString(genericDataDTO))
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isOk());

    }

    @Test
    public void whenCallPost_sendReturnBadRequest() throws Exception {

        GenericDataDTO genericDataDTO = GenericDataDTO.builder()
                .policyId(1)
                .billToDate(true)
                .build();

        BDDMockito.given(genericDataChangesService.send(any(GenericDataDTO.class))).willReturn(false);

        mvc.perform(
                post("/policy/change")
                        .content(mapper.writeValueAsString(genericDataDTO))
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());

    }

}
