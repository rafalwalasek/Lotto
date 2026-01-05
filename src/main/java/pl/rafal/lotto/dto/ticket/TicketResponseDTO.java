package pl.rafal.lotto.dto.ticket;

import lombok.Data;

import java.util.List;

@Data
public class TicketResponseDTO {
    private int id;
    private List<Integer> numbers;
}
