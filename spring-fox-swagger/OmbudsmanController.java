
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ombudsman")
public class OmbudsmanController implements OmbudsmanDocumetation {

    @Autowired
    private SuggestionChangesService suggestionChangesService;

    @PostMapping(value = "/cadastre")
    public ResponseEntity<String> send(@RequestBody SuggestionVO suggestionVO) throws BusinessException {

            log.info("enviando para topico kafka...");

            if(suggestionChangesService.send(suggestionVO)) {

                return ResponseEntity.ok().build();
            }else{

                return ResponseEntity.badRequest().build();
            }
    }

}
