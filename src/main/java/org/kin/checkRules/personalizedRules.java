package org.kin.checkRules;

/**
 * @description:
 * 特有规则，存放了一些特有的计算规则
 *
 * @author: chq
 * @time: 2021/8/6 16:40
 */
public class personalizedRules extends baseRules {


//    public static void userCheck(){
//        double x =1.0d;
//        baseRules.isNegative(x);
//
//    }

    /**
     * 用水量检测
     */
    public static boolean checkConsumption(double current,double target ){
        //若当前用水量小于阈值则正常，大于阈值则异常
        if(current>target){
            return false;
        }else{
            return true;
        }
    }

}
