package com.ms.email.adapters.outbound.persistence.entities;

import com.ms.email.application.domain.enumerations.Situation;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "EMAIL")
public class EmailEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime sendDate;

    @Enumerated(EnumType.STRING)
    private Situation situation;
}
