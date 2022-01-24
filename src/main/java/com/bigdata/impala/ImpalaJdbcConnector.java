package com.bigdata.impala;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// impala 连接器
// 当前: 准备接口形式，更加方便一点
@RestController
@RequestMapping("/impala")
public class ImpalaJdbcConnector {

    private static final String impalaDriverName = "com.cloudera.impala.jdbc41.Driver";

    private static Logger logger = LogManager.getLogger(ImpalaJdbcConnector.class);
    
    @PostMapping("/query")
    public List<String> impalaQuerySQL(@RequestBody ImpalaQuery query) {
        Connection conn = null;
        List<String> retList = new ArrayList<String>();
        try {
            // 加载 jdbc 连接
			Class.forName(impalaDriverName);
			conn = DriverManager.getConnection(query.getConnectionUrl());
			Statement stmt = conn.createStatement();

            // 执行 SQL
            String toExecuteSql = query.getQuery();
            logger.info("[impala_jdbc] begin to execute sql: {}", toExecuteSql);
			ResultSet rs = stmt.executeQuery(toExecuteSql);
			while (rs.next()) {
                logger.info("[impala_jdbc] ret column: {}", rs.getString(1));
                retList.add(rs.getString(1));
			}
            logger.info("[impala_jdbc] success execute sql: {}", toExecuteSql);
		}
        catch (Exception e) {
            logger.warn("[impala_jdbc] execute sql failed, please check: {}", e.getMessage());
        } finally {
            try {
                if (null != conn) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                logger.warn("[impala_jdbc] close resource with exception: {}", e.getMessage());
            }
        }
        return retList;
    }
}
