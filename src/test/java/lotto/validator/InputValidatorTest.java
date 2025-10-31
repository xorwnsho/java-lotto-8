package lotto.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void setUp(){
        inputValidator = new InputValidator();
    }

    @DisplayName("유효한 문자열을 입력했을 때 정상 반환되어야 한다.")
    @ParameterizedTest
    @CsvSource({"1000, 1000", "3000, 3000", "10000, 10000"})
    void validatePurchaseAmount_WhenNumeric_ReturnInt(String input, int expected){
        //when
        int result = inputValidator.validatePurchaseAmount(input);

        //then
        assertThat(result).isEqualTo(expected);
    }



    @DisplayName("입력 값이 숫자 형식이 아니라면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000a", "1000 "})
    @NullAndEmptySource
    void validatePurchaseAmount_WhenNotNumeric_shouldThrowException(String input){
        // when&then
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 숫자여야 합니다.");
    }

    @DisplayName("1000원 단위의 숫자 형식이 아니 에러 메시지를 출력한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234", "5555", "56789"})
    void validatePurchaseAmount_WhenNotMultipleOf1000_ShouldThrowException(String input){
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @DisplayName("0이하의 값을 입력하면 에러 메시지를 출력한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-100"})
    void ValidatePurchaseAmount_WhenNegative_ShouldThrownException(String input){
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }

    @DisplayName("당첨 번호가 6개가 아닐 때 예외를 발생시킨다")
    @Test
    void numberIsNotSix(){
        //given
        String input = "1,2,3,4,5,6,7";

        //when&then
        assertThatThrownBy(() -> inputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("숫자가 아닌 값이 섞여있을 때 예외를 발생시킨다.")
    @Test
    void numberHasNotNumeric(){
        //given
        String input = "1,2,3,4,5,a";

        //when&then
        assertThatThrownBy(() -> inputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 숫자여야 합니다.");
    }

    @DisplayName("1~45 범위가 벗어난 숫자가 있을 때 예외를 발생시킨다.")
    @Test
    void numberIsOverMinAndMax(){
        //given
        String input = "1,2,3,4,5,1000";

        //when&then
        assertThatThrownBy(() -> inputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("중복된 숫자가 있을 때 예외를 발생시킨다.")
    @Test
    void numberIsDuplicated() {
        //given
        String input = "1, 1, 3, 4, 5, 6";

        //when&then
        assertThatThrownBy(() -> inputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

}