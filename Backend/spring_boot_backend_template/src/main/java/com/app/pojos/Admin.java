package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin_signup")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {
    @Column(length = 40, unique = true)
    private String email;

    @Column(length = 50)
    private String password;
}
