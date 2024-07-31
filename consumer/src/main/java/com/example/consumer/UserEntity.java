package com.example.consumer;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @SequenceGenerator(
            name = "UserMessage_id_sequence",
            sequenceName = "UserMessage_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UserMessage_id_sequence"
    )
    @Id
    private Integer id;
    private String name;
    private String email;
    private Date timestamp;
}
