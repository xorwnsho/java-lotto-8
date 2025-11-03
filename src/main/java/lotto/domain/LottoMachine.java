package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    // 20번 기능 : 구입 금액으로 로또 개수 계산
    public int calculateLottoCount(int purchaseAmount){
        return purchaseAmount / LOTTO_PRICE;
    }

    // 21번 기능 : 개수만큼 로또 발행
    public List<Lotto> issueLottos(int lottoCount){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }
}
