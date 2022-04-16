package com.bentbase.backend.admin;

import com.bentbase.backend.seller.Seller;
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
@PrimaryKeyJoinColumn (name = "user_email")
@Table (name = "admin")
public class Admin extends Seller {

}
