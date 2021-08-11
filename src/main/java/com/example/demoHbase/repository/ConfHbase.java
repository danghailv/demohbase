package com.example.demoHbase.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class ConfHbase {
    public static Configuration getconfig() {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","myhbase");
        config.set("hbase.zookeeper.property.clientPort","2181");
        return  config;
    }
}
