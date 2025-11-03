package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("기능 20 : 구입 금액에 따라 로또 개수를 정확히 계산한다.")
    @ParameterizedTest
    @CsvSource({"1000, 1", "8000, 8", "14000, 14"})
    void calculateLottoCount_ShouldReturnCorrectCount(int purchaseAmount, int expectedCount){
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when
        int count = lottoMachine.calculateLottoCount(purchaseAmount);

        // then
        assertThat(count).isEqualTo(expectedCount);
    }

    @DisplayName("기능 21 : 요청된 게수만큼 로또를 발행한다.")
    @Test
    void issueLottos_ShouldReturnCorrectSizeList(){
        //given
        LottoMachine lottoMachine = new LottoMachine();
        int count = 3;

        //when
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    List<Lotto> lottos = lottoMachine.issueLottos(count);
                    // then
                    assertThat(lottos).hasSize(count); // 리스트의 크기가 3인지 확인

                    // 발행된 로또의 번호가 시뮬레이션된 값과 일치하는지 확인
                    // Lotto 클래스에서 오름차순 정렬을 보장해야 함
                    assertThat(lottos.get(0).getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
                    assertThat(lottos.get(1).getNumbers()).isEqualTo(List.of(7, 8, 9, 10, 11, 12));
                    assertThat(lottos.get(2).getNumbers()).isEqualTo(List.of(13, 14, 15, 16, 17, 18));
                },
                List.of(1, 2, 3, 4, 5, 6),   // 1번째 로또 번호 (정렬됨)
                List.of(7, 8, 9, 10, 11, 12), // 2번째 로또 번호 (정렬됨)
                List.of(13, 14, 15, 16, 17, 18) // 3번째 로또 번호 (정렬됨)
        );
    }

}