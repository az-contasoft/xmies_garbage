package az.contasoft.xmies_garbage.db.repo;
import az.contasoft.xmies_garbage.db.entities.Garbage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Repository
public interface RepoGarbage extends JpaRepository<Garbage,Long> {

    List<Garbage> findByIdDepo(long idDepo);
    Garbage findByIdGarbage(long idGarbage);
    List<Garbage> findAll();
    List<Garbage> findByIsConfirm(int isConfirm);
    List<Garbage> findAllByDateGreaterThanEqualAndDateLessThanEqualAndIsConfirm(LocalDateTime date1,
                                                                                LocalDateTime date2, int isConfirm);



}
