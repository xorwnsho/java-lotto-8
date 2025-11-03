package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
