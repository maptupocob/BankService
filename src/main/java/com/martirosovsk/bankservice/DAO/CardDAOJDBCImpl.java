package com.martirosovsk.bankservice.DAO;

import com.martirosovsk.bankservice.domain.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class CardDAOJDBCImpl implements CardDAO {
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String login;

    private static final String NEW_CARD =
            "INSERT INTO CARDS (NUMBER, ACCOUNT_ID) " +
                    "VALUES ( ?, (SELECT ID FROM ACCOUNTS WHERE ID = ?));";
    private static final String FIND_ALL_CARDS_BY_ACCOUNT_ID =
            "SELECT * FROM CARDS WHERE ACCOUNT_ID = ? ORDER BY ID;";

    @Override
    public Set<Card> findAllByAccountID(int id) {
        Set<Card> result = new LinkedHashSet<>();
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement st = connection.prepareStatement(FIND_ALL_CARDS_BY_ACCOUNT_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getInt("ID"));
                card.setAccountID(rs.getInt("ACCOUNT_ID"));
                card.setNumber(rs.getString("NUMBER"));
                result.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Что-то пошло не так...");
        } finally {
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int saveCard(Card card) {
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement ps = connection.prepareStatement(NEW_CARD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, card.getNumber());
            ps.setInt(2, card.getAccountID());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Значение NULL не разрешено для поля \"ACCOUNT_ID\"")) {
                throw new RuntimeException("Невозможно выпустить карту по несуществующему счету");
            } else {
                throw new RuntimeException("Не удалось создать карту");
            }
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

//    private static final String CREATE_TABLE_CARDS = "CREATE TABLE IF NOT EXISTS Cards (" +
//            " id INT AUTO_INCREMENT PRIMARY KEY, " +
//            "    owner_id int NOT NULL, " +
//            "    number VARCHAR(255) NOT NULL)";

}
