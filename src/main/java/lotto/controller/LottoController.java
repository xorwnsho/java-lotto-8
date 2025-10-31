package lotto.controller;

import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

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
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);

        System.out.println(bonusNumber);
    }

    private int getValidPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return inputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> getWinningNumbers() {
        while (true) {
            try {
                String input = inputView.readLottoNumbers();
                return inputValidator.validateWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getBonusNumber(List<Integer> winningNumbers){
        while (true) {
            try {
                String input = inputView.readBonusNumber();
                return inputValidator.validateBonusNumber(input, winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
