
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "Ouvidoria")
public interface OmbudsmanDocumetation {
	
	@ApiOperation(value="Cadastrar Sugestão", response=SuggestionVO.class, notes="Essa operação enviar uma sugestão para um tópico para posteriormente ser salva na base de dados")
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Sucesso", response=SuggestionVO.class),
		@ApiResponse(code=400, message="Mensagem de erro será informada.", response=SuggestionVO.class)
	})
	public ResponseEntity<String> send(@RequestBody SuggestionVO suggestionVO) throws BusinessException;

}
