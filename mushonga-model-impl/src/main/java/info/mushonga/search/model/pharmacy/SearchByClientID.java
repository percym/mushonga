package info.mushonga.search.model.pharmacy;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchByClientID {
    private Long clientId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SearchByClientID(Long clientId, LocalDateTime startDate, LocalDateTime endDate) {
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
