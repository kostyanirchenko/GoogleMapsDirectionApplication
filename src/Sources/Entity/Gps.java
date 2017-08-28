package Sources.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pasha on 28.08.2017.
 */
@Entity
@Table(name = "gps", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "agentId"),
        @UniqueConstraint(columnNames = "address"),
        @UniqueConstraint(columnNames = "lat"),
        @UniqueConstraint(columnNames = "lon")

})
public class Gps implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "agentId", unique = false, nullable = false)
    private int agentId;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "lat", unique = false, nullable = false)
    private double lat;

    @Column(name = "lon", unique = false , nullable = false)
    private double lon;

    public Gps() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
