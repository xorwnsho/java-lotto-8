package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @DisplayName("기능 25 : 일치 개수와 보너스 여부에 따라 정확한 등수(Rank)를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",  // 6개 일치 (1등)
            "5, true, SECOND",  // 5개 일치 + 보너스 (2등)
            "5, false, THIRD",  // 5개 일치 (3등)
            "4, true, FOURTH",  // 4개 일치 (보너스 상관없이 4등)
            "4, false, FOURTH", // 4개 일치 (4등)
            "3, true, FIFTH",   // 3개 일치 (보너스 상관없이 5등)
            "3, false, FIFTH",  // 3개 일치 (5등)
            "2, true, MISS",    // 2개 일치 (꽝)
            "0, false, MISS"    // 0개 일치 (꽝)
    })
    void valueOf_ShouldReturnCorrectRank(int matchCount, boolean matchBonus, Rank expected){
        //when
        Rank result = Rank.valueOf(matchCount, matchBonus);

        //then
        assertThat(result).isEqualTo(expected);
    }
}