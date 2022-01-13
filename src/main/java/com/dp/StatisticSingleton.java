package com.dp;

// singleton
public class StatisticSingleton {
    // static singleton
    private static StatisticSingleton singleton = new StatisticSingleton();

    private StatisticSingleton() {}

    public static StatisticSingleton getSingleton() {
        return singleton;
    }
}
