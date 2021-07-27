package com.basic.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class ProcessExecutorController {

    private static final String command = "test.sh";
    private static final String execDir = "/home/scripts";
    private static final Map<String, String> envMap = new HashMap<String, String>(){{
        put("LANG", "en_US.UTF-8");
    }};
    Logger logger = LogManager.getLogger(ProcessExecutorController.class);

    @GetMapping("/exec")
	public int exec() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("/bin/sh", command);
        builder.directory(new File(execDir));
        builder.environment().putAll(envMap);
        Process process = builder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line;
        while (null != (line = reader.readLine())) {
            logger.info(line);
        }
        return 0;
	}
}
