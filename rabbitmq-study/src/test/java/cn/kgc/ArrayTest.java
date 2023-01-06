package cn.kgc;


import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Author: Kyler
 * Time: 2022/7/8 8:13
 * Target:
 */

public class ArrayTest {
    ArrayTest() {

    }
    @Test
    public void testArray() {
        int[] arr1 = {1,2,3,5,2};
        // System.out.println(Arrays.toString(Arrays.stream(arr1).distinct().toArray()));
        List<Integer> integers = Arrays.asList(ArrayUtils.toObject(arr1));
        Set<Integer> intList = new HashSet<>(integers);
        System.out.println(intList);
    }
}
