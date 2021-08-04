package com.pgjbz.hrworker.model;

import com.pgjbz.hrworker.dto.WorkerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@Table(name = "TB_WORKER")
@SQLInsert(sql = "import.sql")
@NoArgsConstructor(onConstructor = @__(@Deprecated))
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double dailyIncome;

    public WorkerResponseDTO toDto(){
        return new WorkerResponseDTO(this.id, this.name, this.dailyIncome);
    }

}
