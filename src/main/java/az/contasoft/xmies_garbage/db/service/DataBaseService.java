package az.contasoft.xmies_garbage.db.service;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import az.contasoft.xmies_garbage.db.repo.RepoGarbage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataBaseService {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseService.class);
    @Autowired
    RepoGarbage repoGarbage;

    public Garbage saveOrUpdateOrDelete(Garbage garbage) throws Exception{
        logger.info("error to save/update/delete");
        if (garbage.getIdGarbage()==0){
            garbage.setIsConfirm(0);
        }
        return repoGarbage.save(garbage);


    }

    public List<Garbage> getAll()throws Exception{
        logger.info("trying to get ALL garbage");
            return repoGarbage.findByIsConfirm(1);

    }

    public List<Garbage> getGarbageByIdDepo(long idDepo){
        return repoGarbage.findByIdDepo(idDepo);

    }


    public List<Garbage> getAllConfirmedGarbageByDate(LocalDateTime date1, LocalDateTime date2){
        try {
            logger.info("getAllConfirmedGarbageByDate");
            List<Garbage> list = repoGarbage.findAllByDateGreaterThanEqualAndDateLessThanEqualAndIsConfirm(date1,
                                                                                                date2,1);
            return list;
        }catch (Exception e){
            return  null;

        }


    }

    public List<Garbage> getAllNotConfirmedGarbageByDate(LocalDateTime date1, LocalDateTime date2){

        try {
            logger.info("getAllNotConfirmedGarbageByDate");
            List<Garbage> list = repoGarbage.findAllByDateGreaterThanEqualAndDateLessThanEqualAndIsConfirm(date1,
                                                                                                date2,0);
            return list;

        }catch (Exception e){
            return null;


        }
    }




   /* private List<Garbage> getAllByIdDepartamentConfirmed(long idDepartament,int isConfirm){
        return repoGarbage.findByIdDepartamentAndIsConfirm(idDepartament, 1);

    }

    private List<Garbage> getAllByIdDepartamentNotConfirmed(long idDepartament,int isConfirm){
        return repoGarbage.findByIdDepartamentAndIsConfirm(idDepartament, 0);

    }*/


    }


