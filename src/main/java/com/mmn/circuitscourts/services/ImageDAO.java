package com.mmn.circuitscourts.services;

import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class ImageDAO {

    static Connection connection = ConnectionMySQL.getInstance();

    public ImageDAO() {

    }

    public void getAll() {

    }

    public int add(File file) throws SQLException, FileNotFoundException {
        FileInputStream input = new FileInputStream(file);
        String query = "INSERT INTO image(image, ext) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setBinaryStream(1, (InputStream) input, (int) file.length());
        preparedStatement.setString(2, FilenameUtils.getExtension(file.getName()));

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int id = -1;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
    }

    public void getById(int id) throws SQLException, IOException {
        String query = "SELECT image, ext FROM image WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            InputStream inputStream = resultSet.getBinaryStream(1);
            String ext = "." + resultSet.getString(2);
            if (!new File("/data/img"+id+ext).exists()) {
                if (!Files.exists(Paths.get("data/"))) {
                    Files.createDirectories(Path.of(Paths.get("").toAbsolutePath().toString() + File.separator + "data"));
                }
                OutputStream outputStream = new FileOutputStream(new File("data"+File.separator+"img"+id+ext));
                inputStream.transferTo(outputStream);
                /*int b = 0;
                while ((b = inputStream.read()) > -1) {
                    outputStream.write(b);
                }*/
            }
        }
    }

    public void update(int id, File file) throws SQLException, FileNotFoundException {
        FileInputStream input = new FileInputStream(file);
        String query = "UPDATE image SET image=?, ext=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBinaryStream(1, (InputStream) input, (int) file.length());
        preparedStatement.setString(2, FilenameUtils.getExtension(file.getName()));
        preparedStatement.setInt(3, id);

        preparedStatement.executeUpdate();
    }

    public Image getImage(int id, String ext) throws IOException, SQLException {
        File file = new File("data/img"+id+"."+ext);
        if (!file.exists()) {
            getById(id);
        }
        return new Image(file.toURI().toURL().toString());
    }

    public String getExtById(int id) throws SQLException {
        String query = "SELECT ext FROM image WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else throw new SQLException("ID introuvable");
    }
}
