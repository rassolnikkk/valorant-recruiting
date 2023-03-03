package com.example.valorantrecruiting.service;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.repository.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;


@Service
@EnableKafka
@AllArgsConstructor
public class ApplicantService {


    ApplicantRepo applicantRepo;

    public List<Applicant> getALlApplicants(){
        return applicantRepo.findAll();
    }

    public Optional<Applicant> findApplicantById(Long id){
       return applicantRepo.findById(id);
    }

    public void addApplicant(Applicant applicant){
        applicantRepo.save(applicant);
    }
    //methods below  track all data sended from discord eventlistener classes and add it to my database
    @KafkaListener(topics = "rank")
    public void addRank(ConsumerRecord<Long, String> record) {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/valorant_recruiting_db";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, "postgres", "pass");
            Long userId = record.key();
            String userRank = record.value();
            PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM recruit_schema.applicant WHERE id = ?");
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id) VALUES (?)");

            checkStmt.setLong(1, userId);
            try (ResultSet res = checkStmt.executeQuery()) {
                if (res.next()) {
                    PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET rank = ? WHERE id=?");
                    insertRankStmt.setString(1, userRank);
                    insertRankStmt.setLong(2, userId);
                    insertRankStmt.execute();
                    con.close();
                } else {
                    insertStmt.setLong(1, userId);
                    insertStmt.execute();
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "roles")
    public void addRole(ConsumerRecord<Long, String> record)  {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/valorant_recruiting_db";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, "postgres", "pass");
            Long userId = record.key();
            String userRole = record.value();
            PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM recruit_schema.applicant WHERE id = ?");
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id) VALUES (?)");
            checkStmt.setLong(1, userId);
            try (ResultSet res = checkStmt.executeQuery()) {
                if (res.next()) {
                    PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET role = ? WHERE id=?");
                    insertRankStmt.setString(1, userRole);
                    insertRankStmt.setLong(2, userId);
                    insertRankStmt.execute();
                    con.close();
                } else {
                    insertStmt.setLong(1, userId);
                    insertStmt.execute();
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "trackerlink")
    public void addLink(ConsumerRecord<Long, String> record)  {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/valorant_recruiting_db";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, "postgres", "pass");
            Long userId = record.key();
            String userLink = record.value();
            PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM recruit_schema.applicant WHERE id = ?");
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id) VALUES (?)");
            checkStmt.setLong(1, userId);
            try (ResultSet res = checkStmt.executeQuery()) {
                if (res.next()) {
                    PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET valo_tracker_reference = ? WHERE id=?");
                    insertRankStmt.setString(1, userLink);
                    insertRankStmt.setLong(2, userId);
                    insertRankStmt.execute();
                    con.close();
                } else {
                    insertStmt.setLong(1, userId);
                    insertStmt.execute();
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @KafkaListener(topics = "age")
    public void addAge(ConsumerRecord<Long, String> record)  {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/valorant_recruiting_db";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, "postgres", "pass");
            Long userId = record.key();
            System.out.println(record.value().getClass());
            String userAge = record.value();
            PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM recruit_schema.applicant WHERE id = ?");
            PreparedStatement insertStmt = con.prepareStatement("INSERT INTO recruit_schema.applicant (id) VALUES (?)");
            checkStmt.setLong(1, userId);
            try (ResultSet res = checkStmt.executeQuery()) {
                if (res.next()) {
                    Integer convertedUserAge = Integer.valueOf(userAge);
                    PreparedStatement insertRankStmt = con.prepareStatement("UPDATE recruit_schema.applicant SET age = ? WHERE id=?");
                    insertRankStmt.setInt(1, convertedUserAge);
                    insertRankStmt.setLong(2, userId);
                    insertRankStmt.execute();
                    con.close();
                } else {
                    insertStmt.setLong(1, userId);
                    insertStmt.execute();
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}