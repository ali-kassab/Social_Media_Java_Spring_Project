//package com.example.SpringBootMVC.user;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
//@Component
//
//public class TestDBConnection implements CommandLineRunner {
//    private final DataSource dataSource;
//    public TestDBConnection(DataSource dataSource) { this.dataSource = dataSource; }
//
//    @Override
//    public void run(String... args) throws Exception {
//        try (var conn = dataSource.getConnection()) {
//            System.out.println("Connection successful: " + conn.getMetaData().getURL());
//        }
//    }
//}

