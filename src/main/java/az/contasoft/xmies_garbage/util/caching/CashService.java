package az.contasoft.xmies_garbage.util.caching;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import az.contasoft.xmies_garbage.db.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;


@Component
public class CashService {
    private static final Logger logger = LoggerFactory.getLogger(CashService.class);
    @Autowired
    RedisService redisService;

    @Autowired
    private DataBaseService dataBaseService;


    public Garbage saveOrUpdateOrDelete(Garbage garbage){
        try {
            garbage = dataBaseService.saveOrUpdateOrDelete(garbage);
            if (garbage.getIsConfirm()==1){
                logger.info("error to remove garbage from map");
                redisService.remove(RedisMapKey.MAP_OF_GARBAGE,garbage.getIdGarbage());
            }else{
                logger.info("error to put garbage to map");
                redisService.add(garbage.getIdGarbage(),garbage,RedisMapKey.MAP_OF_GARBAGE);
            }
            return garbage;

        }catch (Exception e){
            logger.error("error saveOrUpdateOrDelete e : {}, e : {}", e, e);
            return null;


        }
      }
    private Map<Long, Garbage> mapOfGarbage;

    public Map<Long, Garbage> getGetMapOfGarbage(){
        try {
            try {
                logger.info("trying to get mapOfGarbage from redis");
                mapOfGarbage = redisService.get(RedisMapKey.MAP_OF_GARBAGE);

            }catch (Exception e){
                logger.error("error getMapOfGarbage from redis e: {}, e: {}", e, e);
            }
            if (mapOfGarbage==null||mapOfGarbage.isEmpty()){
                logger.info("mapOfGarbage not found from redis trying to cache");
                mapOfGarbage=startCaching();
              }
            return mapOfGarbage;
        }catch (Exception e){
            logger.info("error getMapOfGarbage e: {}, e: {}", e, e);
            return null;
         }
         }

         public void addToRedisMap(){
            logger.info("addToRedisMap");
            try {
                redisService.addMap(mapOfGarbage, RedisMapKey.MAP_OF_GARBAGE);
                logger.info("MAP_OF_GARBAGE size : {}",redisService.get(RedisMapKey.MAP_OF_GARBAGE).size());
               }catch (Exception e){
                logger.info("error addToRedisMap e : {}, e : {}", e, e);


            }

         }

         public Map<Long,Garbage> startCaching(){
            try {
                redisService.destroyMap(RedisMapKey.MAP_OF_GARBAGE);
                List<Garbage> listOfGarbageFromDB = dataBaseService.getAll();
                if (listOfGarbageFromDB==null||listOfGarbageFromDB.isEmpty()){
                    logger.info("listOfGarbageFromDB null ve ya isEmpty, cache olmayacaq");
                        return null;

                }else{
                    logger.info("listOfGarbageFromDB.size(): {}",listOfGarbageFromDB.size());

                    for (Garbage garbage : listOfGarbageFromDB) {
                        mapOfGarbage.put(garbage.getIdGarbage(), garbage);
                    }

                    new Thread(this::addToRedisMap).start();



                    return mapOfGarbage;
                }
            }catch (Exception e){

                logger.error("error startCachingProtokol e: {}, e: {}", e, e);
                    return null;



            }

         }


    public Garbage getGarbageByIdGarbage(long idGarbage) {
        try {
            Map<Long,Garbage> mapOfGarbage = redisService.get(RedisMapKey.MAP_OF_GARBAGE);
            return mapOfGarbage.get(idGarbage);

        }catch (Exception e){
            System.out.println("Error getGarbageByIdGarbage "+e);
            return null;
        }
    }


    public List<Garbage> getGarbageByIdDepo(long idDepo){
        try {
      Map<Long,Garbage> mapOfGarbage = redisService.get(RedisMapKey.MAP_OF_GARBAGE);
        return dataBaseService.getGarbageByIdDepo(idDepo);
        }catch (Exception e){
            System.out.println("Error getGarbageByIdDepo"+e);
            logger.info("getGarbageByIdDepo");
            return null;


        }

    }



}
