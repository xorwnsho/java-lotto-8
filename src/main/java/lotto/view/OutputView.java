package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    // 12번 기능 : 구매한 로또 개수 출력
    public void printLottoCount(int count){
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    // 13번 기능 : 구매한 로또 번호 목록 출력 (오름차순)
    public void printLottos(List<Lotto> lottos){
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    // 14번 기능 : 당첨 통계 및 구분선 ("---") 출력
    public void printStatisticsHeader(){
        System.out.println("\n당첨통계");
        System.out.println("---");
    }
}
