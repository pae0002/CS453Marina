package MarinaWebService;

import javax.xml.ws.Endpoint;
import MarinaWebService.MarinaWebServiceImpl;
import java.sql.*;
 
//Endpoint publisher
public class MarinaWebServicePublisher{

    public static void main(String[] args) throws Exception {
        Endpoint.publish("http://localhost:9999/ws/marina", new MarinaWebServiceImpl());
    }  
}