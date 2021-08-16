package org.kin.checkRules;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @description:
 * @author: chq
 * @time: 2021/8/13 10:51
 */
public class flinkCheck extends baseRules {

//    public static void main(String[] args) throws IOException {
////        ArrayList<Double> ls =new ArrayList<Double>();
////        ls.add(6.0D);
////        ls.add(3.0);
////        ls.add(6.0d);
////        ls.add(4.0d);
////        ls.add(6.0d);
////        ls.add(7.0d);
////        ls.add(3.0d);
////        ls.add(56.0d);
////        ls.add(64.0d);
////        ls.add(38.0d);
////        ls.add(45.0d);
////        ls.add(27.0d);
////        ls.add(29.0d);
////        ls.add(6.0d);
////        ls.add(6.0d);
////        ls.add(4.0d);
////        ls.add(6.0d);
////        ls.add(7.0d);
////        ls.add(3.0d);
////        ls.add(6.0d);
////        ls.add(3.0d);
////        ls.add(8.0d);
////        ls.add(5.0d);
////        ls.add(7.0d);
////        ls.add(9.0d);
////        double curentv= 7.0d;
////
////        String st = userCheck(ls,curentv);
//
////        System.out.println(map.toString()  );
//    }


    private static Map<String, String> mapPros = new HashMap();
    static {
        Properties prop = new Properties();
        InputStream in = flinkCheck.class.getClassLoader().getResourceAsStream("anomalous.properties");
        try {
            prop.load(in);
            Set<Object> keys = prop.keySet();//返回属性key的集合
            for (Object key : keys) {
                mapPros.put(key.toString(), prop.get(key).toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 私有构造方法，不需要创建对象
     */
    private flinkCheck() {
    }


    public static String userCheck(ArrayList<Double> ls, double curentV ) throws IOException {



        double[] x = ls.stream().mapToDouble(i->i).toArray();

        String flag = "";

        //是否负
        boolean isNeg = baseRules.isNegative(curentV);
        if(isNeg){
            flag += mapPros.get("LessZero");

//            flag += "正反累小于零";
        }

        //是否长时间不变
        boolean isLbl = baseRules.isLongTimeBalance(x,curentV);
        if(isLbl){
            flag += mapPros.get("LongBlance");
//            flag += "数据长时间不变";
        }

        //是否突变
        boolean isMut = baseRules.isMutation(x,curentV);
        if(isMut){
            flag += mapPros.get("dataIsBigboo");
//            flag += "超大值数据";
        }

        //是否归0
        boolean isTo0 = baseRules.isRegressionZero(x,curentV);
        if(isTo0){
            flag += mapPros.get("ToZero");
//            flag += "归零异常";
        }

        return flag;
    }


}
