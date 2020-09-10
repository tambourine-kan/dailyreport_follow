package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RelationShip {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private Employee employee_following;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Employee employee_follower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployeeFollowing() {
        return employee_following;
    }

    public void setEmployeeFollowing(Employee employee_following) {
        this.employee_following = employee_following;
    }

    public Employee getEmployeeFollower() {
        return employee_follower;
    }

    public void setEmployeeFollower(Employee employee_follower) {
        this.employee_follower = employee_follower;
    }

}