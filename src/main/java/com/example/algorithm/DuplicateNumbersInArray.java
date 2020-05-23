package com.example.algorithm;

import java.util.*;

/**
 * @author clark
 * @Description:
 * @date 2020/5/23 15:02
 */
public class DuplicateNumbersInArray {

    public static void main(String[] args) {
        int[] arrays = new int[]{1, 2, 3, 4, 5, 6, 3, 4};
    }

    /**
     * 详解:https://blog.nowcoder.net/n/808e31c3b2424647a3743aad6e2831e7?f=comment
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }

        return -1;
    }
}
