package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 범위 테스트용 데이터
    static Stream<List<Integer>> provideNumbersOutOfRange(){
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(0, 1, 2, 3, 4, 5)
        );
    }

    @DisplayName("기능 22 : 로또 번호가 생성될 때 오름차순으로 정렬되어야 한다.")
    @Test
    void createLotto_ShouldBeSorted(){
        //given
        List<Integer> numbers = List.of(6, 5, 4, 3, 2, 1);

        //when
        Lotto lotto = new Lotto(numbers);

        //then
        assertThat(lotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("기능 23 : 두 로또 번호 간에 일치하는 번호의 개수를 정확히 변환한다.")
    @Test
    void countMatchingNumbers_ShouldReturnCorrectCount(){
        //given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 7, 8, 9));

        //when
        int matchCount = lotto1.countMatchingNumbers(lotto2);

        //then
        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("기능 24 : 로또가 특정 번호를 포함하는지 정확히 확인한다.")
    @ParameterizedTest
    @CsvSource({"1, true", "7, false"})
    void contains_ShouldReturnCorrectBoolean(int number, boolean expected){
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when
        boolean result = lotto.contains(number);

        //then
        assertThat(result).isEqualTo(expected);
    }


}
