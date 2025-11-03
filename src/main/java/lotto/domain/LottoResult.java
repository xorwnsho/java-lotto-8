package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> statistics;

    public LottoResult(){
        this.statistics = new EnumMap<>(Rank.class);
        for(Rank rank : Rank.values()){
            statistics.put(rank, 0);
        }
    }

    // 기능 15, 26 : 등수(Rank)를 받아서 통계에 추가(1 증가)
    public void addResult(Rank rank){
        statistics.put(rank, statistics.get(rank) + 1);
    }

    // 전체 통계 앱을 반환
    public Map<Rank, Integer> getStatistics(){
        return Collections.unmodifiableMap(statistics);
    }

    // 기능 27 : 총 당첨 금액 계산
    public long calculateTotalPrize(){
        long totalPrize = 0;
        for(Rank rank : statistics.keySet()){
            totalPrize += (long) rank.getPrizeMoney() * statistics.get(rank);
        }
        return totalPrize;
    }

    // 기능 17, 18, 19번 : 총 수익률 계산
    public double calculateProfitRate(int purchaseAmount){
        long totalPrize = calculateTotalPrize();

        if(purchaseAmount == 0)
            return 0.0;

        double profitRate = (double) totalPrize / purchaseAmount * 100.0;

        return Math.round(profitRate * 10.0) / 10.0;
    }
}
