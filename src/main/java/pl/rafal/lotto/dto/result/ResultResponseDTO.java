package pl.rafal.lotto.dto.result;

import lombok.Data;

import java.util.List;

@Data
public class ResultResponseDTO {
    private List<Integer> ticketNumbers;
    private List<Integer> drawNumbers;
    private int hintCount;
}
