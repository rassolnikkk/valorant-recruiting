package com.example.valorantrecruiting.kafka.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.sql.*;
@EnableKafka
public class KafkaListeners {

    private static final String checkStmtSqlQuery = "SELECT id FROM recruit_schema.applicant WHERE id = ?";
    private static final String url = "jdbc:postgresql://localhost:5432/valorant_recruiting_db";
    private static final String username = "postgres";
    private static final String password = "pass";


    private static Connection getDbConnection() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
    private static void initializeDriver(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //methods below  track all data sent from discord eventlistener classes and add it to my database
    @KafkaListener(topics = "rank")
    public void addRank(ConsumerRecord<Long, String> record) {
        initializeDriver();
        try (Connection con = getDbConnection()){
            Long userId = record.key();
            String userRank = record.value();
            PreparedStatement checkStmt = con.prepareStatement(checkStmtSqlQuery);
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id, rank) VALUES (?,?)");
            checkStmt.setLong(1, userId);
            ResultSet res = checkStmt.executeQuery();
            if (res.next()) {
                PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET rank = ? WHERE id=?");
                insertRankStmt.setString(1, userRank);
                insertRankStmt.setLong(2, userId);
                insertRankStmt.execute();
                res.close();
            } else {
                insertStmt.setLong(1, userId);
                insertStmt.setString(1, userRank);
                insertStmt.execute();
                res.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "roles")
    public void addRole(ConsumerRecord<Long, String> record)  {
        initializeDriver();
        try (Connection con = getDbConnection()){
            Long userId = record.key();
            String userRole = record.value();
            PreparedStatement checkStmt = con.prepareStatement(checkStmtSqlQuery);
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id, role) VALUES (?,?)");
            checkStmt.setLong(1, userId);
            ResultSet res = checkStmt.executeQuery();
            if (res.next()) {
                PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET role = ? WHERE id=?");
                insertRankStmt.setString(1, userRole);
                insertRankStmt.setLong(2, userId);
                insertRankStmt.execute();
                res.close();
            } else {
                insertStmt.setLong(1, userId);
                insertStmt.setString(2, userRole);
                insertStmt.execute();
                res.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "trackerlink")
    public void addLink(ConsumerRecord<Long, String> record)  {
        initializeDriver();
        try (Connection con = getDbConnection()){
            Long userId = record.key();
            String userLink = record.value();
            PreparedStatement checkStmt = con.prepareStatement(checkStmtSqlQuery);
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id, valo_tracker_reference) VALUES (?,?)");
            checkStmt.setLong(1, userId);
            ResultSet res = checkStmt.executeQuery();
            if (res.next()) {
                PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET valo_tracker_reference = ? WHERE id=?");
                insertRankStmt.setString(1, userLink);
                insertRankStmt.setLong(2, userId);
                insertRankStmt.execute();
                res.close();
            } else {
                insertStmt.setLong(1, userId);
                insertStmt.setString(2, userLink);
                insertStmt.execute();
                res.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @KafkaListener(topics = "age")
    public void addAge(ConsumerRecord<Long, String> record)  {
        initializeDriver();
        try (Connection con = getDbConnection()){
            Long userId = record.key();
            String userAge = record.value();
            PreparedStatement checkStmt = con.prepareStatement(checkStmtSqlQuery);
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id, age) VALUES (?,?)");
            checkStmt.setLong(1, userId);
            ResultSet res = checkStmt.executeQuery();
            if (res.next()) {
                Integer convertedUserAge = Integer.valueOf(userAge);
                PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET age = ? WHERE id=?");
                insertRankStmt.setInt(1, convertedUserAge);
                insertRankStmt.setLong(2, userId);
                insertRankStmt.execute();
                res.close();
            } else {
                Integer convertedUserAge = Integer.valueOf(userAge);
                insertStmt.setLong(1, userId);
                insertStmt.setInt(2, convertedUserAge);
                insertStmt.execute();
                res.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
