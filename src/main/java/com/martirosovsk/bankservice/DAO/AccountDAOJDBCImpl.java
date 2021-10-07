package com.martirosovsk.bankservice.DAO;

import com.martirosovsk.bankservice.domain.Account;
import com.martirosovsk.bankservice.domain.Enums.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AccountDAOJDBCImpl implements AccountDAO {
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String login;

    //    public static final String NEW_ACCOUNT = "INSERT INTO ACCOUNTS (CLIENT_ID, NUMBER, CURRENCY) VALUES (?, ?, ?)";
    private static final String UPDATE_BALANCE_BY_ACCOUNT_ID = "UPDATE ACCOUNTS SET " +
            "BALANCE = ((SELECT BALANCE FROM ACCOUNTS WHERE ID = ?) + ?) " +
            "WHERE ID = ?";
    private static final String FIND_ALL_ACCOUNTS_BY_CLIENT_ID = "SELECT * FROM ACCOUNTS WHERE CLIENT_ID = ? ";
    private static final String FIND_ACCOUNT_BY_ID = "SELECT * FROM ACCOUNTS WHERE ID = ? ";
    private static final String FIND_ACCOUNT_BY_NUMBER = "SELECT * FROM ACCOUNTS WHERE NUMBER = ? ";


    @Override
    public Account findById(int accountId) {
        Account acc;
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement st = connection.prepareStatement(FIND_ACCOUNT_BY_ID)) {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            rs.next();
            acc = new Account();
            acc.setId(rs.getInt("ID"));
            acc.setClientID(rs.getInt("CLIENT_ID"));
            acc.setNumber(rs.getInt("NUMBER"));
            acc.setCurrency(Currency.valueOf(rs.getString("CURRENCY")));
            acc.setBalance(rs.getInt("BALANCE"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Нет такого счета");
        } finally {
            ConnectionManager.closeConnection(connection);
        }
        return acc;
    }

    @Override
    public Set<Account> findAllByClientId(int clientId) {
        Set<Account> set = new HashSet<>();
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement st = connection.prepareStatement(FIND_ALL_ACCOUNTS_BY_CLIENT_ID)) {
            st.setInt(1, clientId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = createAccountFromResultSet(rs);
                set.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }
        return set;
    }

    @Override
    public void updateBalance(int accountId, int amount) {
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement updateBalance = connection.prepareStatement(UPDATE_BALANCE_BY_ACCOUNT_ID)) {
            connection.setAutoCommit(false);
            updateBalance.setInt(1, accountId);
            updateBalance.setInt(2, amount);
            updateBalance.setInt(3, accountId);
            updateBalance.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Невозможно откатить изменения в базе данных");
            }
            throw new RuntimeException("Ошибка при изменении баланса");
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public Account findByNumber(int number) {
        Connection connection = ConnectionManager.getConnection(driverName, url, login, password);
        try (PreparedStatement findByNumber = connection.prepareStatement(FIND_ACCOUNT_BY_NUMBER)) {
            findByNumber.setInt(1, number);
            ResultSet rs = findByNumber.executeQuery();
            if (rs.next()) {
                return createAccountFromResultSet(rs);
            } else {
                throw new RuntimeException("Не удается найти счет с таким номером");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Ошибка при обращении к базе данных");
        }
    }

    private Account createAccountFromResultSet(ResultSet rs) throws SQLException {
        Account acc = new Account();
        acc.setId(rs.getInt("ID"));
        acc.setClientID(rs.getInt("CLIENT_ID"));
        acc.setNumber(rs.getInt("NUMBER"));
        acc.setCurrency(Currency.valueOf(rs.getString("CURRENCY")));
        acc.setBalance(rs.getInt("BALANCE"));
        return acc;
    }
}
