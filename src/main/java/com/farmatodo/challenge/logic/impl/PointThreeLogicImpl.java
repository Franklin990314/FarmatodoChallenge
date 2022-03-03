package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dto.NumberDTO;
import com.farmatodo.challenge.dto.NumbersDTO;
import com.farmatodo.challenge.logic.PointThreeLogic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PointThreeLogicImpl implements PointThreeLogic {

    @Override
    public NumbersDTO calculateHappyNumbers(String strNumbers) throws Exception {
        NumbersDTO response = new NumbersDTO();
        String[] numbers = strNumbers.split(",");
        Set<Long> happyNumber = new LinkedHashSet<>();
        Set<Long> unhappyNumbers = new LinkedHashSet<>();

        for (String item : numbers) {
            if (!this.isValidNumber(item)) throw new Exception("'"+item+"' no es posible calcular si es feliz o no");

            Boolean isHappy;
            if (happyNumber.contains(new Long(item))) {
                isHappy = new Boolean(true);
            } else if (unhappyNumbers.contains(new Long(item))) {
                isHappy = new Boolean(false);
            } else {
                isHappy = this.isHappyNumber(Long.parseLong(item));
                if (isHappy) happyNumber.add(new Long(item));
                else unhappyNumbers.add(new Long(item));
            }

            NumberDTO number = new NumberDTO();
            number.setNumber(new Long(item));
            number.setIsHappy(isHappy);

            if (response.getNumbers() == null) response.setNumbers(new ArrayList<>());
            response.getNumbers().add(number);
        }

        return response;
    }

    private boolean isValidNumber(String item) {
        if (item.chars().allMatch(Character::isDigit)) {
            try {
                Long.parseLong(item);
                return true;
            } catch (NumberFormatException exc) { }
        }
        return false;
    }

    private Boolean isHappyNumber(long number) {
        if (number == 1) return new Boolean(true);
        Set<Long> calculatedNumbers = new LinkedHashSet<>();

        while (!calculatedNumbers.contains(number)) {
            if (number == 1) return new Boolean(true);
            calculatedNumbers.add(new Long(number));
            long newNumber = 0;
            String strNumber = String.valueOf(number);
            for (Character character : strNumber.toCharArray()) {
                newNumber += (long) Math.pow(Character.getNumericValue(character), 2);
            }
            number = newNumber;
        }

        return new Boolean(false);
    }
}
