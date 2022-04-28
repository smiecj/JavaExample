package com.basic.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jline.console.ConsoleReader;

@RestController
@RequestMapping("/hive")
public class HiveCommandController {

    // private static final String hiveDriverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static final String hiveDriverName = "org.apache.hive.jdbc.HiveDriver";
    // private static final String hiveConnectionStr = "jdbc:hive2://hiveserver_host:10000";
    // todo: 连接串放到配置文件中
    private static final String hiveConnectionStr = "jdbc:hive2://zk_host1:2181,zk_host2:2181,zk_host3:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";

    Logger logger = LogManager.getLogger(HiveCommandController.class);

    @GetMapping("/read")
	public List<String> read(String filePath) throws IOException {
        List<String> retList = new ArrayList<>();
        FileInputStream fs = new FileInputStream(filePath);
        System.out.println("ready to open file");
        ConsoleReader reader = new ConsoleReader(fs, System.out);
        // 解析 tab
        reader.setCopyPasteDetection(true);
        String line;
        System.out.println("ready to read line");
        while ((line = reader.readLine()) != null) {
            System.out.println("current line: " + line);
            retList.add(line);
        }
        return retList;
	}

    // hive connect demo
    // https://cwiki.apache.org/confluence/display/Hive/HiveClient
    // https://help.aliyun.com/document_detail/138719.html
    @GetMapping("/connect")
	public void connect() throws Exception {
      try {
        Class.forName(hiveDriverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ;
        }
        Connection con = DriverManager.getConnection(hiveConnectionStr, "hive", "");
        Statement stmt = con.createStatement();
        // show tables
        String sql = "show tables";
        System.out.println("Running: " + sql);
        stmt.execute("use stg");
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            // System.out.println(res.getString(1) + "\t" + res.getString(2));
            System.out.println(res.getString(1));
        }
	}
}
