package lotto.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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


}