package az.contasoft.xmies_garbage.searchServices.InternalServices;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import az.contasoft.xmies_garbage.db.repo.RepoGarbage;
import az.contasoft.xmies_garbage.db.service.DataBaseService;
import az.contasoft.xmies_garbage.util.caching.CashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@Component
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);
    @Autowired
    private CashService cashService;
    @Autowired
    RepoGarbage repoGarbage;

    @Autowired
    DataBaseService dataBaseService;


    public ResponseEntity<Map<Long, Garbage>> getAllMap(){
        try {
            Map<Long, Garbage> MapOfGarbage = cashService.getGetMapOfGarbage();
            if (MapOfGarbage==null ||MapOfGarbage.isEmpty()){
                logger.info("mapOfLogin not found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("mapOfLogin size: {}",MapOfGarbage.size());
                return new ResponseEntity<>(MapOfGarbage,HttpStatus.OK);
        }catch (Exception e){
            logger.info("error getAllAsMap e: {}, e: {}", e, e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<Garbage> findByIdGarbage(long idGarbage){
        Garbage garbage = cashService.getGarbageByIdGarbage(idGarbage);
        if (garbage==null){
            garbage = repoGarbage.findByIdGarbage(idGarbage);

        }
        return new ResponseEntity<>(garbage,HttpStatus.OK);
    }





    public ResponseEntity<List<Garbage>> findByIdDepo(long idDepo){

       List<Garbage> lisGarbage = cashService.getGarbageByIdDepo(idDepo);

        return new ResponseEntity<>(lisGarbage,HttpStatus.OK);
    }


    public ResponseEntity<List<Garbage>> findByDateAndAllConfirmed(LocalDate data1, LocalDate data2){
        try {
            logger.info("findByDateAndAllConfirmed");
            List<Garbage> listGarbage = dataBaseService.getAllConfirmedGarbageByDate(data1.atStartOfDay(),
                                                                            data2.atTime(23,59));
            return new ResponseEntity<>(listGarbage,HttpStatus.OK);

        }catch (Exception e){
            return null;


        }


    }
    public ResponseEntity<List<Garbage>> findByDateAndAllNotConfirmed(LocalDate data1, LocalDate data2){
        try {
            logger.info("findByDateAndNotAllConfirmed");
            List<Garbage> listGarbage = dataBaseService.getAllNotConfirmedGarbageByDate(data1.atStartOfDay(),
                                                                              data2.atTime(23,59));
            return new ResponseEntity<>(listGarbage,HttpStatus.OK);

        }catch (Exception e){
            return null;


        }




    }






}
