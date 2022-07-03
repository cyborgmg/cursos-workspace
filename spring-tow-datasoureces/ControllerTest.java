import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {
                LoveCardController.class,
                LoveCardService.class,
                TbeneService.class
        })
@AutoConfigureJson
@AutoConfigureWebMvc
@AutoConfigureMockMvc(addFilters =false)
@ComponentScan("br.com.xxxxxxxx")
public class LoveCardControllerTest {

    @MockBean
    private LoveCardService loveCardService;

    @MockBean
    private TbeneService beneService;

    @MockBean
    private TcliService cliService;

    @Autowired
    private LoveCardController loveCardController;

    @Test
    public void injectControllerTest() {
        assertNotNull(loveCardController);
    }

    @Test
    public void whenCall_saveLoveCardOk() {

        lenient().when(beneService.beneficiaryExist(any(String.class))).thenReturn(true);
        lenient().when(cliService.tcliExist(any(String.class))).thenReturn(true);
        lenient().when(loveCardService.save(any(LoveCard.class))).thenReturn(LOVE_CARD.getLoveCard());

        ResponseEntity responseEntity = loveCardController.saveLoveCard(CLI_ID,LOVE_CARD);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void whenCall_saveLoveCard_beneficiaryExist_false() {

        lenient().when(beneService.beneficiaryExist(any(String.class))).thenReturn(false);
        lenient().when(cliService.tcliExist(any(String.class))).thenReturn(true);

        ResponseEntity responseEntity = loveCardController.saveLoveCard(CLI_ID,LOVE_CARD);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}