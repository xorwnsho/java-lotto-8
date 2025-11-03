package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp(){
        // 정답지 : 1, 2, 3, 4, 5, 6 + 보너스 7
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @DisplayName("기능 24, 25 : 1등(6개 일치)을 정확히 판별한다.")
    @Test
    void match_ShouldReturnFirst(){
        //given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5 ,6));

        //when
        Rank rank = winningLotto.match(userLotto);

        //then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("기능 24, 25: 2등(5개 + 보너스 일치)을 정확히 판별한다")
    @Test
    void match_ShouldReturnSecond() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 보너스(7) 포함
        // when
        Rank rank = winningLotto.match(userLotto);
        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("기능 24, 25: 3등(5개 일치)을 정확히 판별한다")
    @Test
    void match_ShouldReturnThird() {
        // given
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8)); // 보너스(7) 미포함
        // when
        Rank rank = winningLotto.match(userLotto);
        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

}