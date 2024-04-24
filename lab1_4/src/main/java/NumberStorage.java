//Уровень 4
//На базе коллекций реализуйте структуру хранения чисел с поддержкой
//следующих операций: добавление/удаление числа; поиск числа, наиболее
//близкого к заданному (т.е. модуль разницы минимален).

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberStorage {

    private List<Integer> numbers = new ArrayList<>();

    public void add(int number) throws IllegalArgumentException {
        Optional.of(numbers)
                .filter(list -> list.contains(number))
                .ifPresentOrElse(list -> {
                    throw new IllegalArgumentException(Constants.NUMBER_ALREADY_EXISTS);
                }, () -> numbers.add(number));
    }

    public void remove(int number) throws IllegalArgumentException {
        Optional.of(numbers)
                .filter(list -> !list.contains(number))
                .ifPresentOrElse(list -> {
                    throw new IllegalArgumentException(Constants.NUMBER_NOT_FOUND);
                }, () -> numbers.remove(numbers.indexOf(number)));
    }

    public Integer findClosest(int target) throws IllegalStateException {
        if (numbers.isEmpty()) {
            throw new IllegalStateException(Constants.NUMBER_STORAGE_IS_EMPTY);
        }

        int closest = numbers.get(0);
        int minDifference = Math.abs(target - closest);

        for (int num : numbers) {
            int difference = Math.abs(target - num);
            if (difference < minDifference) {
                minDifference = difference;
                closest = num;
            }
        }

        return closest;
    }


}
