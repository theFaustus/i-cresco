package com.evil.inc.icresco.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrowthPlan> growthPlans = new ArrayList<>();

    public void addGrowthPlan(GrowthPlan growthPlan) {
        growthPlans.add(growthPlan);
        growthPlan.setUser(this);
    }

    public void removeGrowthPlan(GrowthPlan growthPlan) {
        growthPlans.remove(growthPlan);
        growthPlan.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}