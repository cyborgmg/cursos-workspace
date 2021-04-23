import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;

@RunWith(MockitoJUnitRunner.class)
public class GenericDataChangesServiceTest {

    @InjectMocks
    private GenericDataChangesService genericDataChangesService;

    @Mock
    private GenericDataUpdateAsyncService genericDataUpdateAsyncService;


    @Test
    public void whenCall_sendWaitFalse(){

        GenericDataDTO genericDataDTO = GenericDataDTO
                .builder()
                .policyId(1)
                .billToDate(true)
                .build();

        try {
            Mockito.lenient().when(genericDataUpdateAsyncService.singleService(eq(genericDataDTO))).thenThrow(BusinessException.class);

            assertFalse(genericDataChangesService.send(genericDataDTO));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void whenCall_sendWaitTrue() {

        GenericDataDTO genericDataDTO = GenericDataDTO
                .builder()
                .policyId(1)
                .billToDate(true)
                .build();

        try {
            Mockito.lenient().when(genericDataUpdateAsyncService.singleService(eq(genericDataDTO))).thenReturn(any(RestApiFeedback.class));

            assertTrue(genericDataChangesService.send(genericDataDTO));
        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

}