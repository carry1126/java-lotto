package lotto.oop.domain;

import lotto.oop.domain.LottoIssue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoIssueTest {
    private LottoIssue lotto;

    @BeforeEach
    public void setUp() {
        lotto = new LottoIssue(14);
    }

    @Test
    @DisplayName("발행한 로또의 개수 확인")
    public void publishLootListTest() {
        lotto.publishLottoList();
        assertThat(lotto.getResultList().size()).isEqualTo(14);
    }

    @ParameterizedTest
    @ValueSource(strings = {"46 47 48 49 50 51"})
    void checkNumberTest(String numbers) {
        String[] numList = numbers.split("");
        assertThatThrownBy(() -> {
            lotto.checkNumber(numList, "26");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
