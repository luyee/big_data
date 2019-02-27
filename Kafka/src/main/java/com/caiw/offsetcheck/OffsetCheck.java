package com.caiw.offsetcheck;


import kafka.tools.ConsumerOffsetChecker;

public class OffsetCheck {

        public static void main(String[] args)
        {
            //适用于kafka0.8.2.0
            String[] arr = new String[]{"--zookeeper=dsgcd4121:2181,dsgcd4122:2181,dsgcd4123:2181","--topic=cwTest001"};
            //适用于kafka0.8.1
//      String[] arr = new String[]{"--zkconnect=h71:2181,h72:2181,h73:2181","--group=test-consumer-group"};
            ConsumerOffsetChecker.main(arr);
        }
}
