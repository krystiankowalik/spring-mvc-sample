package org.example.web.controller;

import org.example.data.MyDaoImpl;
import org.example.message.QueueMsgSender;
import org.example.model.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class SampleController {


    @Autowired
    private SomeObject myBeanId;

//    @Autowired
//    private DriverManagerDataSource myDataSource;

    @Autowired
    private MyDaoImpl myDaoImpl;

    @Autowired
    private QueueMsgSender queueMsgSender;




    @GetMapping("/hello")
    public String check(){
        return myBeanId.getText();
    }

//    @GetMapping("/mydb")
//    public String mydb() throws SQLException {
//        return myDataSource.getConnection().createStatement().executeQuery("SELECT * FROM mydb.some_object").toString();
//    }

    @GetMapping("/mydb2")
    public String mydb2() throws SQLException {
        return myDaoImpl.getSomeObjects();
    }
    @GetMapping("/jms")
    public String testJms(){
        queueMsgSender.sendMessage("hello",UUID.randomUUID().toString());
        return "on jms endpoint";
    }

}
