package org.kin.checkRules;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @description:
 * @author: chq
 * @time: 2021/8/13 10:51
 */
public class flinkCheck extends baseRules {
//
//    public static void main(String[] args) throws IOException {
//        ArrayList<Double> ls =new ArrayList<Double>();
//        ls.add(6.0D);
//        ls.add(3.0);
//        ls.add(6.0d);
//        ls.add(4.0d);
//        ls.add(6.0d);
//        ls.add(7.0d);
//        ls.add(3.0d);
//        ls.add(56.0d);
//        ls.add(64.0d);
//        ls.add(38.0d);
//        ls.add(45.0d);
//        ls.add(27.0d);
//        ls.add(29.0d);
//        ls.add(6.0d);
//        ls.add(6.0d);
//        ls.add(4.0d);
//        ls.add(6.0d);
//        ls.add(7.0d);
//        ls.add(3.0d);
//        ls.add(6.0d);
//        ls.add(3.0d);
//        ls.add(8.0d);
//        ls.add(5.0d);
//        ls.add(7.0d);
//        ls.add(9.0d);
//        double curentv= 7.0d;
//
//        String st = userCheck(ls,curentv);
//
//        System.out.println(st);
//    }


    public static String userCheck(ArrayList<Double> ls, double curentV ) throws IOException {
//        Properties pros = new Properties();
//        FileInputStream in = new FileInputStream("resources/anomalous.properties");
//        pros.load(in);
//        in.close();

        double[] x = ls.stream().mapToDouble(i->i).toArray();


        String flag = "";

        //是否负
        boolean isNeg = baseRules.isNegative(curentV);
        if(isNeg){
//            flag += pros.get("正反累小于零");
            flag += "正反累小于零";
        }

        //是否长时间不变
        boolean isLbl = baseRules.isLongTimeBalance(x,curentV);
        if(isLbl){
//            flag += pros.get("数据长时间不变");
            flag += "数据长时间不变";
        }

        //是否突变
        boolean isMut = baseRules.isMutation(x,curentV);
        if(isMut){
//            flag += pros.get("超大值数据");
            flag += "超大值数据";
        }

        //是否归0
        boolean isTo0 = baseRules.isRegressionZero(x,curentV);
        if(isTo0){
//            flag += pros.get("归零异常");
            flag += "归零异常";
        }

        return flag;
    }


}
