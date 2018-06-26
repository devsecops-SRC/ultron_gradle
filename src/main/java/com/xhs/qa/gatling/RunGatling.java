package com.xhs.qa.gatling;

import io.gatling.app.Gatling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author：wuqihua on 2017/11/16 15:29
 */
public class RunGatling {
    public static void main(String[] args){

        System.out.printf("\n=================压测服务开始启动=================\n");

        List<String> argsGatLing = new ArrayList<>();
        try {
            for (int i = 0; i < args.length; ++i) {
                if ("-st".equals(args[i])) {
                    ++i;
                    String stStr = args[i];
                    if (stStr.length() != 13) throw new Exception("-st value length should be 13");
                    long st = Long.valueOf(stStr);
                    System.out.printf("=================脚本预期执行时间为" + new Date(st).toString() + "=================\n");

                    boolean isWhile = true;

                    while (isWhile) {
                        long ts = new Date().getTime();
                        long timeDiff = st - ts;
                        if (timeDiff > 60000) {
                            Thread.sleep(30000);
                        } else if (timeDiff > 10000) {
                            Thread.sleep(1000);
                        } else if (timeDiff <= 0) {
                            isWhile = false;
                        }
                    }

                } else {
                    argsGatLing.add(args[i]);
                }
            }

            System.out.printf("=================压测脚本开始执行，现在的时间是" + new Date().toString()+"=================\n");

            Gatling.main(argsGatLing.toArray(new String[argsGatLing.size()]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
