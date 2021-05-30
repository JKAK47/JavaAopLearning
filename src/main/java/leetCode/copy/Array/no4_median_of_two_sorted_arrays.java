package leetCode.copy.Array;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class no4_median_of_two_sorted_arrays {
    /**
     * 二分法 参考官网
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int k = (m + n) / 2;
        if ((m + n) % 2 == 1) {
            return getKthElement(nums1, nums2, k + 1);
        } else {
            return (getKthElement(nums1, nums2, k) + getKthElement(nums1, nums2, k + 1)) / 2.0;
        }
    }

    /** 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较,这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     *
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     */
    private int getKthElement(int nums1[], int nums2[], int k) {
        int index1 = 0, index2 = 0;
        while (true) {
            if (index1 == nums1.length) {
                return nums2[index2 + k - 1];
            }
            if (index2 == nums2.length) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1 + k - 1], nums2[index2 + k - 1]);
            }
            int half = k / 2;
            int index11 = Math.min(index1 + half, nums1.length) - 1;
            int index22 = Math.min(index2 + half, nums2.length) - 1;
            if (nums1[index11] <= nums2[index22]) {
                k = k - (index11 - index1 + 1);
                index1 = index11 + 1;

            } else {
                k = k - (index22 - index2 + 1);
                index2 = index22 + 1;
            }
        }
    }

    /**
     * 二分法 参考官网视频
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int temp[ ] = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;
        int n = nums2.length;
        int mid = (m+n+1)/2;
        int left = 0,right = m;
        while(left < right){
            int i = (left + right+1)/2;
            int j = mid - i;

            if(nums1[i-1] > nums2[j]){
                right = i-1;
            }else{
                left = i;
            }
        }
        int i = left;
        int j = mid - i;
        int num_i1 = i==0?Integer.MIN_VALUE:nums1[i-1];
        int num_i = i==m?Integer.MAX_VALUE:nums1[i];
        int num_j1 = j==0?Integer.MIN_VALUE:nums2[j-1];
        int num_j = j==n?Integer.MAX_VALUE:nums2[j];

        return (m+n)%2==0?(Math.max(num_i1,num_j1)+Math.min(num_i,num_j))/2.0:Math.max(num_i1,num_j1);
    }
    /**
     * 官方解答真简洁
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0,right = m;
        int mid1 = 0,mid2 = 0;
        while(left <= right){
            int i = (left + right)/2;
            int j = (m+n+1)/2 - i;
            int num_i1 = i==0?Integer.MIN_VALUE:nums1[i-1];
            int num_i = i==m?Integer.MAX_VALUE:nums1[i];
            int num_j1 = j==0?Integer.MIN_VALUE:nums2[j-1];
            int num_j = j==n?Integer.MAX_VALUE:nums2[j];
            if(num_i1<= num_j){
                mid1 = Math.max(num_i1,num_j1);
                mid2 = Math.min(num_i,num_j);
                left = i+1;
            }else{
                right = i-1;
            }

        }
        return (m+n)%2==0?(mid1+mid2)/2.0:mid1;
    }

    public static void main(String args[]){
        no4_median_of_two_sorted_arrays obj = new no4_median_of_two_sorted_arrays();
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{2,3},new int[]{2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{2,3},new int[]{}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{},new int[]{2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{1},new int[]{}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{3,4},new int[]{3,4}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{3,4},new int[]{1,2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{0,0},new int[]{0,0}));
    }
}
