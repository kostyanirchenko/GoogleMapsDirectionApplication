package Sources.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pasha on 28.08.2017.
 */

@Entity
@Table(name = "groups", uniqueConstraints = {
        @UniqueConstraint(columnNames = "groupId"),
        @UniqueConstraint(columnNames = "groupName")
})
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupId", unique = true, nullable = false)
    private int groupId;

    @Column(name = "groupName", unique = true, nullable = false)
    private String groupName;

    public Groups() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
