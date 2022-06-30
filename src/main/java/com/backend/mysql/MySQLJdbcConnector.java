package com.backend.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// mysql 连接器
// todo: 和 impala 逻辑非常类似，可合并
@RestController
@RequestMapping("/mysql")
public class MySQLJdbcConnector {

    private static final String mysqlDriverName = "com.mysql.cj.jdbc.Driver";

    private static Logger logger = LogManager.getLogger(MySQLJdbcConnector.class);

    @PostMapping("/query")
    public List<String> mysqlQuerySQL() {
        Connection conn = null;
        List<String> retList = new ArrayList<String>();
        try {
            // 加载 jdbc 连接
			Class.forName(mysqlDriverName);
			conn = DriverManager.getConnection("jdbc:mysql://mysql_host:3306/temp", "user", "pwd");
			Statement stmt = conn.createStatement();

            // 执行 SQL
            String toExecuteSql = "SELECT * FROM test";
            logger.info("[mysql_jdbc] begin to execute sql: {}", toExecuteSql);
			ResultSet rs = stmt.executeQuery(toExecuteSql);
			while (rs.next()) {
                logger.info("[mysql_jdbc] ret column: {}", rs.getString(1));
                retList.add(rs.getString(1));
                logger.info("[test] ret: " + rs.getObject(1) + ", " + rs.getObject(2));
			}
            logger.info("[mysql_jdbc] success execute sql: {}", toExecuteSql);
		}
        catch (Exception e) {
            logger.warn("[mysql_jdbc] execute sql failed, please check: {}", e.getMessage());
        } finally {
            try {
                if (null != conn) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                logger.warn("[mysql_jdbc] close resource with exception: {}", e.getMessage());
            }
        }
        return retList;
    }
}
