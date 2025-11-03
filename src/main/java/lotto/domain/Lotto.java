package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new java.util.ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        // 2번 기능 : 1~45 범위인지 검증
        for (Integer number : numbers) {
            if(number < 1 || number > 45){
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }

        // 9번 기능 : 중복이 없는지 검증
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(uniqueNumbers.size() != numbers.size()){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    // 13번 기능 (출력)을 위해 numbers를 외부에 제공하는 메서드
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    // TODO: 추가 기능 구현
}
