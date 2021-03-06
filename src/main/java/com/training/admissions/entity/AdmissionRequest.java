package com.training.admissions.entity;


import com.training.admissions.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admission_request",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"candidate_id", "faculty_id"})})

@Entity
public class AdmissionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "status", nullable = false)
    private AdmissionRequestStatus admissionRequestStatus;

    @ManyToOne(targetEntity = Candidate.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(targetEntity = Faculty.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "req_subject1_grade", nullable = false)
    private int requiredSubject1Grade;
    @Column(name = "req_subject2_grade", nullable = false)
    private int requiredSubject2Grade;
    @Column(name = "req_subject3_grade", nullable = false)
    private int requiredSubject3Grade;


    @CreationTimestamp
    @Column(name = "creation_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDateTime;





    public int getSumOfGrades() {
        return getRequiredSubject1Grade() + getRequiredSubject2Grade() + getRequiredSubject3Grade();
    }

    public String getStringDateTime() {
        return DateTimeUtil.toString(getCreationDateTime());

    }


    @Override
    public String toString() {
        return "AdmissionRequest{" +
                "id=" + id +
                ", status=" + admissionRequestStatus +
                ", requiredSubject1Grade=" + requiredSubject1Grade +
                ", requiredSubject2Grade=" + requiredSubject2Grade +
                ", requiredSubject3Grade=" + requiredSubject3Grade +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}