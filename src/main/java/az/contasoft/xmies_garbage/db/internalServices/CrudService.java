package az.contasoft.xmies_garbage.db.internalServices;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import az.contasoft.xmies_garbage.util.caching.CashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
public class CrudService {

    private static final Logger logger = LoggerFactory.getLogger(CrudService.class);

    @Autowired
    private CashService cashService;

     public ResponseEntity<Garbage> saveOrUpdateOrDeleteGarbage(Garbage garbage){
        try {
            logger.info("error to save/update/delete");
            garbage = cashService.saveOrUpdateOrDelete(garbage);
            if (garbage== null){
                logger.info("no action");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }else{
                logger.info("success");
                return new ResponseEntity<>(garbage, HttpStatus.OK);
            }
             }catch (Exception e){
                logger.info("error saveOrUpdateOrDeleteLogin e: {}, e: {}", e, e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

//final
//RepoGarbage repoGarbage;
//
//public CrudService(RepoGarbage repoGarbage){
//  this.repoGarbage = repoGarbage;
//}



//public Garbage savegar(Garbage garbage){
//    repoGarbage.save(garbage);
//    return garbage;
//}
//
//public String deletegar(long idGarbage){
//    Garbage g =new Garbage();
//    g.setIdGarbage(idGarbage);
//    g.setIsConfirm(1);
//    repoGarbage.delete(g);
//    return "Vasha vibrannay data udalena";
//}

//public Garbage saveOrUpdateOrDelete(Garbage garbage) throws Exception{
//    logger.info("error to save/update/delete");
//    return (garbage);
//
//}
//
//public List<Garbage> getAll() throws Exception{
//    logger.info("error to getAll");
//    return repoGarbage.findByIsConfirm(0);
//
//}
//
//public Garbage getGarbage(long idGarbage) throws Exception{
//    logger.info("error to getGarbage");
//    return repoGarbage.findByIdGarbageAndIsConfirm(idGarbage, 0);
//
//}



}













