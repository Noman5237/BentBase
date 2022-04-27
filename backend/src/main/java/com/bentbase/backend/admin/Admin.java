package com.bentbase.backend.admin;

import com.bentbase.backend.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "admin")
@PrimaryKeyJoinColumn (name = "user_email")
public class Admin extends User {

}
