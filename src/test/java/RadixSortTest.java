import org.dlpk.Exercicio5.RadixSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RadixSortTest {
    private int[] randInts(int min, int max, int quant) {
        return new Random().ints(quant, min, max).toArray();
    }

    @Test
    public void negativosNaoPode() {
        int[] arr = {-1,-2,3,4};
        assertThrows(IllegalArgumentException.class, () -> RadixSort.sort(arr));
    }

    @Test
    public void firstSmallest() {
        int[] arr = {4,2,3,1,6,5};
        assertEquals(1, RadixSort.sort(arr)[0] );
    }


    @Test
    public void lastBiggest() {
        int[] arr = {4,2,3,1,6,5};
        assertEquals(6, RadixSort.sort(arr)[arr.length - 1] );
    }

    @Test
    public void vazio() {
        int[] arr = {};
        assertEquals(RadixSort.sort(arr), arr);
    }

    @Test
    public void umNumero() {
        int[] arr = {1};
        assertEquals(1, RadixSort.sort(arr)[0]);
        assertEquals(1, RadixSort.sort(arr)[arr.length - 1]);
    }

    @Test
    public void sortGiant() {
        int[] arr = randInts(Integer.MAX_VALUE/2, Integer.MAX_VALUE, 1000);
        int[] arr_sorted = Arrays.stream(arr).sorted().toArray();

        assertArrayEquals(arr_sorted, RadixSort.sort(arr));
    }

    @Test
    public void sortPowersOf2() {
        List<Integer> lst = new ArrayList<>();
        for (int i = 64; i > 0; i--) {
            lst.add((int) Math.pow(2, i));
        }
        int[] arr = lst.stream().mapToInt(i -> i).toArray();

        assertEquals(128, RadixSort.sort(arr)[6] );
    }



}
