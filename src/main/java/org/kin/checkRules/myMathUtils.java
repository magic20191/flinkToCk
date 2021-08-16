package org.kin.checkRules;

/**
 * @description:
 * 异常检测方程工具类
 *
 * @author: chq
 * @time: 2021/8/6 17:15
 */
public class myMathUtils {

    /**
     *
     * 计算前后数据的阶梯差，然后返回数组。
     * @param nums  double[]数组
     * @return  返回处理好的前后数据阶梯差值数据组  [(x2-x1),(x3-x2) .... (xn-x[n-1])]
     */
    public double[] GetStepDifs(double[] nums){

        double[] ret = new double[nums.length-1];

        //循环减前个数得到差值
        for (int i=1;  i< nums.length;i++){
            Double temp = nums[i] - nums[i-1];
            ret[i-1] = temp ;
        }
        return ret;
    }


    /**
     * 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n 或者s^2=[(x1-x)^2 +...(xn-x)^2]/(n-1)
     * @param x  double[]
     * @return double 方差
     */
    public static double variance(double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        return dVar/m;
    }


    /**
     * 标准差σ=sqrt(s^2)
     *
     * @param x double[]数组
     * @return 标准差σ
     */
    public static double standardDiviation(double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        return Math.sqrt(dVar/(m-1));  //无偏估计
//        return Math.sqrt(dVar/m);    //有偏估计
    }

    /**
     * 获取众数
     * 此处的众数是指值之间差异值在限制值 正负l 内的
     * @param x double[]待计算数组
     * @param l int 限制阈值
     * @return 众数值
     */
    public static double getModelNum(double[] x , int l){


        for(int i=0;i<x.length;i++){
            long a = Math.round(x[i]);

        }


        return 1.0d;
    }


//    public int majorityElement(int[] nums) {
//        int num = nums.length >> 1;
//
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(nums[i])) {
//                map.put(nums[i], map.get(nums[i]) + 1);
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > num) {
//                return entry.getKey();
//            }
//        }
//
//        return 0;
//    }



}
