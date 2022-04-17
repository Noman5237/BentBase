package com.bentbase.backend.seller;

import com.bentbase.backend.user.rest.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "seller")
@PrimaryKeyJoinColumn (name = "user_email")
public class Seller extends User {

}
