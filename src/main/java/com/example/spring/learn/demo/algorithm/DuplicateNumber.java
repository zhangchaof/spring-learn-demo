package com.example.spring.learn.demo.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 13:51
 */
public class DuplicateNumber {

    /**
     * 一、排序
     * 1. 分析
     * 将输入数组排序，再判断相邻位置是否存在相同数字，如果存在，对 duplication 赋值返回，否则继续比较
     * 2.代码如下
     * 3. 复杂度
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public boolean duplicateSort(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

    /**
     * 二、哈希表
     * 1. 分析
     * 利用 HashSet 解决，从头到尾扫描数组，每次扫描到一个数，判断当前数是否存在 HashSet 中，如果存在，则重复，对 duplication 赋值返回，否则将该数加入到 HashSet 中
     * 2. 代码如下
     * 3. 复杂度
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */

    public boolean duplicateHash(int[] numbers, int length, int[] duplication) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                set.add(numbers[i]);
            }
        }
        return false;
    }

    /**
     * 三、利用特性
     * 1. 分析
     * 数组的长度为 n 且所有数字都在 0 到 n-1 的范围内，我们可以将每次遇到的数进行"归位"，当某个数发现自己的"位置"被相同的数占了，则出现重复。
     * 扫描整个数组，当扫描到下标为 i 的数字时，首先比较该数字（m）是否等于 i，如果是，则接着扫描下一个数字；如果不是，则拿 m 与第 m 个数比较。如果 m 与第 m 个数相等，则说明出现重复了；如果 m 与第 m 个数不相等，则将 m 与第 m 个数交换，将 m "归位"，再重复比较交换的过程，直到发现重复的数
     * <p>
     * 举个栗子：
     * 以数组 {2,3,1,0,2,5,3} 为例
     * 当 i = 0 时，nums[i] = 2 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{1,3,2,0,2,5,3}
     * 此时 i = 0，nums[i] = 1 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{3,1,2,0,2,5,3}
     * 此时 i = 0，nums[i] = 3 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{0,1,2,3,2,5,3}
     * 此时 i = 0，nums[i] = 0 = i，继续下一组
     * 当 i = 1，nums[i] = 1 = i，继续下一组
     * 当 i = 2，nums[i] = 2 = i，继续下一组
     * 当 i = 3，nums[i] = 3 = i，继续下一组
     * 当 i = 4，nums[i] = 2 != i，判断 nums[i] 等于 nums[nums[i]]，出现重复，赋值返回
     * <p>
     * 2. 代码如下
     * <p>
     * 3. 复杂度
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                // swap
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return false;
    }

}
