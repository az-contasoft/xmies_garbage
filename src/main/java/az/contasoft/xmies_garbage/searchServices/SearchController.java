package az.contasoft.xmies_garbage.searchServices;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import az.contasoft.xmies_garbage.searchServices.InternalServices.SearchService;
import az.contasoft.xmies_garbage.searchServices.internal.RequestDateSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/searchServices")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/getAllGarbage")
        public ResponseEntity<Map<Long, Garbage>> getAllGarbage(){
            logger.info("Get All Garbage");
            return searchService.getAllMap();
    }

    @GetMapping("/getGarbageByIdGarbage/{idGarbage}")
    public ResponseEntity<Garbage> getGarbageByIdGarbage(@PathVariable("idGarbage") long idGarbage){
        return searchService.findByIdGarbage(idGarbage);

    }

    @GetMapping("getGarbageByIdDepo/{idDepo}")
    public ResponseEntity<List<Garbage>> getGarbageByIdDepo(@PathVariable("idDepo") long idDepo){
        return searchService.findByIdDepo(idDepo);

    }


    @PostMapping("getAllisConfirmedByDate")
    public ResponseEntity<List<Garbage>> getAllListConfirmedByDate(@RequestBody RequestDateSearch requestDateSearch){

        return searchService.findByDateAndAllConfirmed(requestDateSearch.getDate1(),requestDateSearch.getDate2());
    }


    @PostMapping("/getAllisNotConfirmedByDate")
    public ResponseEntity<List<Garbage>> getAllListNotConfirmedByDate(@RequestBody RequestDateSearch requestDateSearch){

        return searchService.findByDateAndAllNotConfirmed(requestDateSearch.getDate1(),requestDateSearch.getDate2());


    }













}
