package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Nassim
 * @author Martin
 * @author Magomed
 * La classe AccountDAO constitue les interractions entre le code et la base de donnée pour tous les comptes stockés dans la base de données.
 */

public class AccountDAO implements DAO<User,Integer> {
    /**
     * @param connection Element statique de type Connection renvoyée par la méthode getInstance() qui assure l'unicité de la connection.
     */
    static Connection connection = ConnectionMySQL.getInstance();

    /**
     * Constructeur vide pour pouvoir instancier la classe AccountDAO
     */
    public AccountDAO() {

    }

    /**
     * Implémentation de la méthode getAll de l'interface DAO.
     * @return Une liste contenant tous les comptes présents dans la base de données
     * @throws SQLException
     */
    @Override
    public ArrayList<User> getAll() throws SQLException {
        String query = "SELECT * FROM accounts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<User> accountsList = new ArrayList<>();
        while (resultSet.next()) {
            accountsList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }

        return accountsList;
    }

    /**
     * Implémentation de la méthode getById de l'interface DAO. Si l'Id n'est pas trouvé l'erreur "ID introuvable" est lancée
     * @param id Id du compte recherché.
     * @return l'objet User instancié avec les données du compte recherché dans la base de données.
     * @throws SQLException
     */
    @Override
    public User getById(Integer id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        } else throw new SQLException("ID introuvable.");
    }

    /**
     * Implémentation de la méthode add de l'interface DAO.
     * @param user Prends en paramètre un objet user avec tous ses paramètres que l'on veut ajouter à la base de données.
     * @return un boolean qui nous indique si l'update s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public int add(User user) throws SQLException {
        String query = "INSERT INTO accounts(identifiant, password, grade) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getIdentifiant());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getGrade());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new RuntimeException();
    }

    /**
     * Implémentation de la méthode update de l'interface DAO.
     * Modifie dans la base de donnée un compte.
     * @param id Id du compte à modifier.
     * @param user un user contenant les nouveaux attributs, l'id du compte reste inchangé.
     * @returnun boolean qui nous indique si l'update s'est bien effectuée.
     * @throws SQLException Retourne une SQL exception si la requête échoue.
     */
    @Override
    public boolean update(Integer id, User user) throws SQLException {
        String query = "UPDATE accounts SET identifiant=?, password=?, grade=? WHERE accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getIdentifiant());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getGrade());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.executeUpdate();
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    /**
     * Implémentation de la méthode remove de l'interface DAO.
     * @param id permet de retrouver l'objet à supprimer.
     * @return boolean qui nous indique si l'update s'est bien effectuée.
     * @throws SQLException Retourne une SQLException si la requête échoue.
     */
    @Override
    public boolean remove(Integer id) throws SQLException {
        String query = "DELETE FROM accounts WHERE accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    /**
     * Retourne le mot de passe correspondant à un nom d'uilisateur.
     * Si l'identifiant est introuvable dans la base de donnée la méthode lance l'exception "Identifiant introuvable".
     * @param identifiant Identifiant du compte à retrouver dans la base de données.
     * @return le mot de passe correspondant à cet identifiant
     * @throws Exception
     */
    public String getPasswordByIdentifiant(String identifiant) throws Exception {
        String query = "SELECT password FROM accounts WHERE identifiant=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, identifiant);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else throw new Exception("Identifiant introuvable.");
    }

    /**
     * Cette méthode sert à comparer le bon mot de passe appartenant à l'utilisateur qui est stoké dans la BD à celui que l'utilisateur vient de rentrer pour se connecter.
     * Si le mot de passe qui viens d'etre saisi ne correspond pas avec celui de la base de données l'erreur "Mot de passe Incorrect" est lancée.
     * L'erreur "Problème!" est lancée si un problème survient lors l'execution de la requête.
     * @param identifiant saisi par l'utilisateur
     * @param password le mot de pass saisi par l'itilisateur
     * @return un nouvel utilisateur.
     * @throws Exception
     */
    public User connect(String identifiant, String password) throws Exception {
        String goodPass = getPasswordByIdentifiant(identifiant);

        if (password.equals(goodPass)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE identifiant=? AND password=?");
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            } else throw new SQLException("Problème !");
        } else throw new Exception("Mot de passe incorrect.");
    }

    /**
     * Permet de récuperer parmis tous les comptes utilisateurs seulemnt les clients via leurs grade.
     * @return une ArrayList contenant tous les clients.
     * @throws SQLException
     */
    public ArrayList<User> getAllClient() throws SQLException {
        String query = "SELECT * FROM accounts WHERE grade = 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<User> accountsList = new ArrayList<>();

        while (resultSet.next()) {
            accountsList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }

        return accountsList;
    }

    /**
     * crée une array list composé d'un nom de compte(obligatoirement d'une entreprise) et du numéro de siret associé à ce compte.
     * @return arrayList
     * @throws SQLException
     */
    public ArrayList<String> getAllEntreprisesNameAndSiret() throws SQLException {
        String query = "SELECT accounts.identifiant, entreprise.numSiret FROM accounts  INNER JOIN entreprise ON accounts.accountId = entreprise.accountID WHERE grade = 2";
        Statement st = connection.createStatement();
        ResultSet resultSet =  st.executeQuery(query);
        ArrayList<String> namesAndSiret = new ArrayList<>();
        while(resultSet.next()){
           namesAndSiret.add(resultSet.getString(1) + "-" + resultSet.getInt(2));
        }
        return namesAndSiret;
    }

    public boolean exists(String identifiant) throws SQLException {
        String query = "SELECT * FROM accounts WHERE identifiant=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, identifiant);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        } else return false;
    }
}
