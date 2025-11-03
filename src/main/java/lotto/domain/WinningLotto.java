package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    // 구매한 로또(userLotto)를 받아 당첨 등수를 반환
    public Rank match(Lotto userLotto){

        // 기능 23 : 몇 개 일치하는지 계산
        int matchCount = userLotto.countMatchingNumbers(this.winningNumbers);

        // 기능 24 : 보너스 번호 일치 여부 계산
        boolean matchBonus = userLotto.contains(this.bonusNumber);

        // 기능 25 : emdtn vkswjd
        return Rank.valueOf(matchCount, matchBonus);
    }
}
