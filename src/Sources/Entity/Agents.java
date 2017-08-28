package Sources.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pasha on 28.08.2017.
 */

@Entity
@Table(name = "agents", uniqueConstraints = {
        @UniqueConstraint(columnNames = "agentId"),
        @UniqueConstraint(columnNames = "FIO")
})
public class Agents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agentId", unique = true, nullable = false)
    private int agentId;

    @Column(name = "FIO", unique = false, nullable = false)
    private String FIO;

    public Agents() {

    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
