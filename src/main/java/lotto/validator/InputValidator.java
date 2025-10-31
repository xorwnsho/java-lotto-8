package lotto.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputValidator {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    // 구입 금액을 검증하고 반환
    public int validatePurchaseAmount(String input){
        int purchaseAmount = parseNumeric(input, ERROR_PREFIX + "구입 금액은 숫자여야 합니다.");
        validateAmountPositive(purchaseAmount);
        validateAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    private int parseNumeric(String input, String errorMessage) {
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // 입력 값이 1,000원 단위인지 확인
    private void validateAmountUnit(int purchaseAmount){
        if(purchaseAmount % LOTTO_PRICE != 0)
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 1,000원 단위여야 합니다.");
    }

    // 입력 값이 양수인지 확인
    private void validateAmountPositive(int input){
        if(input <= 0){
            throw new IllegalArgumentException(ERROR_PREFIX + "구입 금액은 0보다 커야 합니다.");
        }
    }

    public List<Integer> validateWinningNumbers(String input){
        // 기능 7 : 쉼표 기준으로 분리
        String[] numberStrings = splitByComma(input);

        // 기능 8 : 분리된 숫자가 6개인지 확인
        validateNumberCount(numberStrings);

        // 기능 1 : 쉼표로 분리된 값들이 숫자인지 확인
        List<Integer> numbers = convertToIntList(numberStrings);

        // 기능 2 : 6개의 숫자가 1~45 범위인지 확인
        validateNumberRange(numbers);

        // 기능 9 : 6개의 숫자가 중복되지 않는지 확인
        validateNoDuplicates(numbers);

        return numbers;
    }

    private String[] splitByComma(String input) {
        if(!input.contains(",") || input.endsWith(",")){
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 쉼표(,)를 기준으로 구분해야 합니다.");
        }
        return input.split(",");
    }

    private void validateNumberCount(String[] numberStrings) {
        if(numberStrings.length != LOTTO_NUMBER_COUNT){
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 6개여야 합니다.");
        }

    }

    private List<Integer> convertToIntList(String[] numberStrings) {
        try{
            return Arrays.stream(numberStrings)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 숫자여야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if(number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER){
                throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validateNoDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(numbers.size() != uniqueNumbers.size()){
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 중복될 수 없습니다.");
        }
    }

}
