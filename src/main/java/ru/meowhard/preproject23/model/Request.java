package ru.meowhard.preproject23.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean requestStatus = false;

    @OneToOne(mappedBy = "request")
    private User user;

    public Request(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Request(Long id, boolean requestStatus) {
        this.id = id;
        this.requestStatus = requestStatus;
    }

    public boolean getRequestStatus() {
        return this.requestStatus;
    }
}