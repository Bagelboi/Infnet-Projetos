package org.dlpk.Exercicio2;

import java.util.Arrays;
import java.util.OptionalDouble;

public class MathFunctions
{

    public MathLogger logger;

    public MathFunctions(MathLogger logger) {
        this.logger = logger;
    }

    public int MultiplyByTwo(int number)
    {
        return number * 2;
    }
    public int[] GenerateMultiplicationTable(int number, int limit)
    {
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++)
        {
            result[i] = number * (i + 1);
        }
        return result;
    }
    public boolean IsPrime(int number)
    {
        if (number <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++)
        {
            if (number % i == 0)
                return false;
        }

        return true;
    }
    public double CalculateAverage(int[] numbers)
    {
        if (numbers == null || numbers.length == 0) throw new IllegalArgumentException("Array cannot be null or empty.");
        OptionalDouble avg = Arrays.stream(numbers).average();
        return avg.orElseThrow();
    }
}
