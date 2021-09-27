package ch.zuehlke.fullstack.hackathon.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="game")
public class Game implements Serializable {

    @Id
    private long id;
    private int pos;
    private long time;
    private Date date;
}
