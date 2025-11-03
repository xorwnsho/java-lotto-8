package lotto.domain;

import java.util.List;

public enum Rank {
    FIFTH(3, 5_000, false, "3개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    THIRD(5, 1_500_000, false, "4개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치 보너스 볼 일치"),
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    MISS(0, 0, false, "꽝");

    private final int matchCount;
    private final int prizeMoney;
    private final boolean needsBonus;
    private final String description;

    Rank(int matchCount, int prizeMoney, boolean needsBonus, String description){
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.needsBonus = needsBonus;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus){
        if(matchCount == 6){
            return FIRST;
        }
        if(matchCount == 5 && matchBonus){
            return SECOND;
        }
        if(matchCount == 5){
            return THIRD;
        }
        if(matchCount == 4){
            return FOURTH;
        }
        if(matchCount == 3){
            return FIFTH;
        }
        return MISS;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }
    public int getMatchCount() {
        return matchCount;
    }

    public static List<Rank> getRanksForDisplay(){
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }
}
