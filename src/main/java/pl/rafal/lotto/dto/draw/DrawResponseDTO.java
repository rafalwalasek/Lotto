package pl.rafal.lotto.dto.draw;

import lombok.Data;

import java.util.List;

@Data
public class DrawResponseDTO {
    private List<Integer> numbers;
}
