package pl.rafal.lotto.service;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class NumberGeneratorServiceTest {
    private final NumberGeneratorService service = new NumberGeneratorService();

    @Test
    void shouldGenerateSixUniqueNumbersBetween1and49() {
        Set<Integer> numbers = service.generateLottoNumbers();

        assertEquals(6, numbers.size());
        assertTrue(numbers.stream().allMatch(n -> n >=1 && n <=49));
    }
}
