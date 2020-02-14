package az.contasoft.xmies_garbage.db.entities;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "garbageTable")
public class Garbage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGarbage", nullable = false, unique = true)

    private long idGarbage;
    private long idDepo;
    private long idDepartament;
    private LocalDateTime date;
    private int isConfirm;
    private int count;
    private String note;


}


