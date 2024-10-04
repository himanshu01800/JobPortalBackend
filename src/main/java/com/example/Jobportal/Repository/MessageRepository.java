package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    // Custom query to fetch messages between two users
    @Query("SELECT m FROM MessageEntity m WHERE (m.sender = :sender AND m.receiver = :receiver) OR (m.sender = :receiver AND m.receiver = :sender)")
    List<MessageEntity> findMessages(@Param("sender") String sender, @Param("receiver") String receiver);
}
