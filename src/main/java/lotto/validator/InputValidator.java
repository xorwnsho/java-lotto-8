package lotto.validator;

public class InputValidator {

    private static final int LOTTO_PRICE = 1000;

    // 구입 금액을 검증하고 반환
    public int validatePurchaseAmount(String input){
        int purchaseAmount = parseToInt(input);
        validateAmountPositive(purchaseAmount);
        validateAmountUnit(purchaseAmount);
        return purchaseAmount;
    }

    // 입력 값이 숫자 형식인지 확인 - 공통 로직
    public int parseToInt(String input){
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    // 입력 값이 1,000원 단위인지 확인
    public void validateAmountUnit(int purchaseAmount){
        if(purchaseAmount % LOTTO_PRICE != 0)
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    // 입력 값이 양수인지 확인
    public void validateAmountPositive(int input){
        if(input <= 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
    }

}
