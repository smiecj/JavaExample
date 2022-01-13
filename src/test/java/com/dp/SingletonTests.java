package com.dp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SingletonTests {
    @Test
    public void testSingleton() throws Exception {
        // get singleton twice
        StatisticSingleton singleton1 = StatisticSingleton.getSingleton();
        StatisticSingleton singleton2 = StatisticSingleton.getSingleton();

        assertTrue(singleton1 == singleton2);
    }
}
