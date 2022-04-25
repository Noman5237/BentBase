package com.bentbase.backend.gig;

import com.bentbase.backend.core.exception.constraint.Message;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "gig_id_generator", sequenceName = "gig_id_sequence", allocationSize = 1)
@Table (name = "gig")
public class Gig {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "gig_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column
	@NotBlank
	private String title;
	
	@Column
	@NotBlank
	private String about;
	
	@Column
	@Email (message = Message.EMAIL)
	private String seller_email;
}
