package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputValidator = new InputValidator();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();

        // 20번, 21번 기능 (로또 번호)
        int lottoCount = lottoMachine.calculateLottoCount(purchaseAmount);
        List<Lotto> purchasedLottos = lottoMachine.issueLottos(lottoCount);

        // 12번, 13번 기능 (발행 결과 출력)
        outputView.printLottoCount(lottoCount);
        outputView.printLottos(purchasedLottos);

        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);

        outputView.printStatisticsHeader();
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
