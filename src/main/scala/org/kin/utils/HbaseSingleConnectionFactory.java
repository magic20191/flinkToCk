package org.kin.utils;


//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseIOException;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @description: 保证一个jvm只有一个hbase conn
 * @author: Andy  https://www.jianshu.com/p/cb75a508ed28  成都加米谷大数据
 * @time: 2021/12/23 14:01
 */
public class HbaseSingleConnectionFactory {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseSingleConnectionFactory.class);

    private volatile static Connection connection;

    private HbaseSingleConnectionFactory() {}

    public static Connection getConnection(Configuration configuration) throws HBaseIOException {
        if (connection == null) {
            synchronized (HbaseSingleConnectionFactory.class) {
                if (connection == null) {
                    try {
                        connection = ConnectionFactory.createConnection(configuration);
//                        LOGGER.warn("the connection of HBase is created successfully.");
                    } catch ( IOException e) {
//                        LOGGER.error("the connection of HBase is created failed.");
                        throw new HBaseIOException(e);
                    }
                }
            }
        }
        return connection;
    }
}
