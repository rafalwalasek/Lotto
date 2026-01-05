package pl.rafal.lotto.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class NumberGeneratorService {
    private final Random random;
    private Set<Integer> drawNumbers;
    private LocalDate drawDate;

    public NumberGeneratorService() {
        this.random = new Random();
    }

    public Set<Integer> generateLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            numbers.add(random.nextInt(49) + 1);
        }
        return numbers;
    }
    public Set<Integer> getDrawNumbers() {
        LocalDate today = LocalDate.now();
        if (drawNumbers == null || !today.equals(drawDate)) {
            drawNumbers = generateLottoNumbers();
            drawDate = today;
        }
        return drawNumbers;
    }
}
