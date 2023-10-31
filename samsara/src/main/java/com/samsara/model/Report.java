package com.samsara.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "Report")
@TableGenerator(name = "tabReport", initialValue = 0, allocationSize = 1)
public class Report implements Serializable{
	private static final long serialVersionUID = 2039167117297172914L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabReport")
	@Column(name = "id", updatable = false, nullable = false)
	private Long  id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	
	private String cause;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
