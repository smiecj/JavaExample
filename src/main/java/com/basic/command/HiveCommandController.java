package com.basic.command;

import java.io.FileInputStream;
import java.io.IOException;
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

}
