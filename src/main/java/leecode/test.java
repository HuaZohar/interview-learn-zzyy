package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class test {

    public static void main(String[] args) {

        int[] ints = new int[]{-1, 0, 1, 2, -1, -4};
        final List<List<Integer>> lists = threeSum3(ints);

        System.out.println(lists);
    }


    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(nums.length);
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums); //去重核心1
        // 构建答案查找表
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // 逆向查找，由因数查找答案
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {  //去重核心2
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == i + 1 || nums[j - 1] != nums[j]) { //去重核心3
                        int sum = nums[i] + nums[j];
                        sum = -sum;
                        Integer index = map.get(sum);
                        if (index != null) {
                            if (index > j) {
                                result.add(Arrays.asList(nums[i], nums[j], sum));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }



    /*public static List<List<Integer>> threeSum2(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> l1 = null;
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (j == i + 1 || nums[j - 1] != nums[j]) { //去重核心3
                        int tmp = nums[i] + nums[j];
                        if (map.containsKey(-tmp)) {
                            System.out.println(nums[i] + "***" + nums[j] + "***" + -tmp);
                        } else {
                            map.put(nums[j], j);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }*/


    public int[] twoSum(int[] nums, int target) {
        if (nums.length <= 1) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            int splitNum = target - nums[i];
            for (int j = i; j < nums.length; j++) {
                if (splitNum == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        List<Integer> l1 = null;
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        l1 = new ArrayList<>();
                        l1.add(nums[i]);
                        l1.add(nums[j]);
                        l1.add(nums[k]);
                        l1.sort(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1 - o2;
                            }
                        });
                        result.add(l1);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }


}
