package com.bentbase.backend.buyer;

import com.bentbase.backend.user.User;
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
@Table (name = "buyer")
@PrimaryKeyJoinColumn (name = "user_email")
public class Buyer extends User {

}
