package org.kin.checkRules;

/**
 * @description:
 * 公共规则类，存放了基础规则计算法则
 *
 * @author: chq
 * @time: 2021/8/6 16:40
 */
public class baseRules {

    /**
     *
     *判断数值是否突变
     * 在统计学中，如果一个数据分布近似正态分布，那么大约68%的数据值在平均值的一个标准差内，约95%在两个标准差内，约99.7%在三个标准差内。
     * 如果有任何数据点超过标准偏差的3倍，那么这些点很可能是异常或异常值。
     *
     * @param nums  传入一组double数值，
     * @return  true 突变  false 未突变
     */
    protected static boolean isMutation(double[] nums,double nowValue){

      double q = myMathUtils.standardDiviation(nums);   //求标准差
//        System.out.println(q);
      if(q==0){
          return false;
      }
//        double xx =  nowValue/q;
//        System.out.println(xx);
        if( nowValue/q > 3.0 ){
          return true;
      }else{
          return false;
      }

    }

    /**
     * 归0检测
     *
     * @param nums
     * @param nowValue
     * @return
     */
    protected static boolean isRegressionZero(double[] nums,double nowValue){

        if(nowValue != 0)
            return false;
        if(nums.length<1)
            return false;

        //临界点判断，即前一位数大于0（正常值），此刻突变至0值。
        if(nums[nums.length-1] > 0 && nowValue ==0 )
            return true;

        return false;
    }

    /**
     * 数值长时间不变化
     * 可以用方差为0计算
     * @param nums  历史数值组
     * @param nowValue  当前数值
     * @param b  方差近0参数
     * @return
     */
    protected static boolean isLongTimeBalance(double[] nums,double nowValue,double b){

        double[] x = new double[nums.length + 1];

        for(int i = 0; i < nums.length ; i++){
            x[i] = nums[i] ;
        }

        x[nums.length] = nowValue;

        double y = myMathUtils.variance(x);   //求方差

        if( y < b)
            return true;

        return false;
    }

    /**
     * 数值长时间不变化
     * 可以用方差为0计算
     * @param nums  历史数值组
     * @param nowValue  当前数值
     * * @param b  方差近0参数 默认取 0.01
     * @return
     */
    protected static boolean isLongTimeBalance(double[] nums,double nowValue ){

        double b = 0.01 ;

        double[] x = new double[nums.length + 1];

        for(int i = 0; i < nums.length ; i++){
            x[i] = nums[i] ;
        }

        x[nums.length] = nowValue;

        double y = myMathUtils.variance(x);   //求方差

        if( y < b)
            return true;

        return false;

    }

//    /**
//     * 数值跳变检测
//     *
//     * is not work done
//     *
//     * @param nums 历史数值组
//     * @param nowValue 当前数值
//     * @return 是否跳变
//     */
//    public static boolean isJump(double[] nums,double nowValue){
//
//        double avg = Arrays.stream(nums).average().getAsDouble();  //去除异常值的均值
//
//        double[] dis = new double[nums.length];
//
//        for(int i=0;i<nums.length;i++){
//
//            dis[i] = nums[i] - avg ;
//
//        }
//
//        double minDis = Arrays.stream(dis).min().getAsDouble();
//
//        double meanDis = Arrays.stream(dis).average().getAsDouble();
//
//
//        return false;
//    }

    /**
     *
     * 判断传进来的数值是否为负
     * @param num  传入一个double数值
     * @return 返回布尔值 负数返回true 其它返回false
     */
    protected static boolean isNegative(double num){
        if(num<0){
            return true;
        } else{
            return false;
        }
    }


}
