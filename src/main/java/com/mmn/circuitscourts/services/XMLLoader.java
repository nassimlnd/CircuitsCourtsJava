package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLLoader {

    public static void loadConfigData() throws Exception {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "config", "Database.xml");

        File configFile = new File(filePath.toUri());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(configFile);

        document.getDocumentElement().normalize();

        String host = document.getDocumentElement().getElementsByTagName("host").item(0).getTextContent();
        String dbname = document.getDocumentElement().getElementsByTagName("dbname").item(0).getTextContent();
        String login = document.getDocumentElement().getElementsByTagName("login").item(0).getTextContent();
        String password = document.getDocumentElement().getElementsByTagName("password").item(0).getTextContent();

        System.out.println("[DEBUG]DatabaseConfig parsed.");

        ConnectionMySQL.url = "jdbc:mysql://"+ host + "/" + dbname +"?serverTimezone=Europe/Paris";
        ConnectionMySQL.login = login;
        ConnectionMySQL.password = password;
    }
}
