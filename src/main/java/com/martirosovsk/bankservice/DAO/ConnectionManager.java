package com.martirosovsk.bankservice.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    @Value("spring.datasource.url")
    private static String url;
    @Value("spring.datasource.username")
    private static String login;
    @Value("spring.datasource.password")
    private static String password;
    @Value("spring.datasource.driver-class-name")
    private static String className;
//    static {
//        String crdTable = "create table if not exists CARDS\n" +
//                "(\n" +
//                "    ID         INT auto_increment,\n" +
//                "    NUMBER     VARCHAR(25) not null,\n" +
//                "    ACCOUNT_ID INT         not null,\n" +
//                "    constraint CARDS_ACCOUNTS_ID_FK\n" +
//                "        foreign key (ACCOUNT_ID) references ACCOUNTS (ID)\n" +
//                "            on update cascade on delete cascade\n" +
//                ");\n" +
//                "\n" +
//                "create unique index CARDS_ID_UINDEX\n" +
//                "    on CARDS (ID);\n" +
//                "\n" +
//                "create unique index CARDS_NUMBER_UINDEX\n" +
//                "    on CARDS (NUMBER);\n" +
//                "\n" +
//                "alter table CARDS\n" +
//                "    add constraint CARDS_PK\n" +
//                "        primary key (ID);\n";
//
//        String accTable = "create table if not exists ACCOUNTS\n" +
//                "(\n" +
//                "    ID        INT auto_increment,\n" +
//                "    CLIENT_ID INT            not null,\n" +
//                "    NUMBER    INT            not null,\n" +
//                "    CURRENCY  VARCHAR(3)     not null,\n" +
//                "    BALANCE   LONG default 0 not null,\n" +
//                "    constraint ACCOUNTS_TO_CLIENT_FK\n" +
//                "        foreign key (CLIENT_ID) references CLIENTS (ID)\n" +
//                "            on update cascade on delete cascade\n" +
//                ");\n" +
//                "\n" +
//                "create unique index ACCOUNTS_ID_UINDEX\n" +
//                "    on ACCOUNTS (ID);\n" +
//                "\n" +
//                "alter table ACCOUNTS\n" +
//                "    add constraint ACCOUNTS_PK\n" +
//                "        primary key (ID);\n";
//        String cltTable = "create table if not exists CLIENTS\n" +
//                "(\n" +
//                "    ID   INT auto_increment,\n" +
//                "    NAME VARCHAR\n" +
//                ");\n" +
//                "\n" +
//                "create unique index CLIENTS_ID_UINDEX\n" +
//                "    on CLIENTS (ID);\n" +
//                "\n" +
//                "alter table CLIENTS\n" +
//                "    add constraint CLIENTS_PK\n" +
//                "        primary key (ID);";
//        try {
//            Class.forName(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try(Connection connection = DriverManager.getConnection(url, login, password);
//            Statement st = connection.createStatement()) {
//            st.executeUpdate(cltTable);
//            st.executeUpdate(accTable);
//            st.executeUpdate(crdTable);
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
//    }

    /**
     * method for obtaining connection
     *
     * @param driverName driverName
     * @param url        url of DB
     * @param login      login
     * @param password   password
     * @return Connection to DB
     */
    public static Connection getConnection(String driverName, String url, String login, String password) {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Невозможно подключиться к базе");
        }
    }

    /**
     * method for closing connection
     *
     * @param con connection to close
     */
    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
