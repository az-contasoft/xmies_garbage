package az.contasoft.xmies_garbage.searchServices.internal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDateSearch {
    private LocalDate date1;
    private LocalDate date2;

}
