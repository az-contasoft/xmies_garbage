package az.contasoft.xmies_garbage.db.internalServices;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crudServices/redis")
public class CrudController {
    private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

    @Autowired
private  CrudService crudService;

    @PostMapping("/SaveOrUpdateOrDelete")
    public ResponseEntity<Garbage> save(@RequestBody Garbage garbage){
        logger.info("save body : {}", garbage);
        return crudService.saveOrUpdateOrDeleteGarbage(garbage);
}




}
