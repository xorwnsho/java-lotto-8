package lotto.controller;

import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputValidator = new InputValidator();
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();
        System.out.println("검증된 구입 금액 : " + purchaseAmount);
    }

    private int getValidPurchaseAmount() {
        while(true){
            try{
                String input = inputView.readPurchaseAmount();
                return inputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e){
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
