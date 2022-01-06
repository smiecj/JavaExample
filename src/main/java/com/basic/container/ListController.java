package com.basic.container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list")
public class ListController {

    Logger logger = LogManager.getLogger(ListController.class);

    @GetMapping("/append")
	public int exec(String filePath) throws IOException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return 0;
	}

}
