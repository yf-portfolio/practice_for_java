package com.example.ecsitedeveloplearning.ec.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ecsitedeveloplearning.ec.account.model.Account;

import lombok.Data;

@Data
@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account accountId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;

}
