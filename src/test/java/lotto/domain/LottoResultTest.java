package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp(){
        lottoResult = new LottoResult();
    }

    @DisplayName("기능 15, 26 : 등수별 당첨 횟수를 정확히 집계한다.")
    @Test
    void addResult_ShouldCountRanksCorrectly(){
        //given
        lottoResult.addResult(Rank.FIFTH);
        lottoResult.addResult(Rank.FIFTH);
        lottoResult.addResult(Rank.FOURTH);
        lottoResult.addResult(Rank.MISS);

        //when
        Map<Rank, Integer> statistics = lottoResult.getStatistics();

        //then
        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(2);
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(statistics.get(Rank.MISS)).isEqualTo(1);
        assertThat(statistics.get(Rank.FIRST)).isEqualTo(0);
    }

}