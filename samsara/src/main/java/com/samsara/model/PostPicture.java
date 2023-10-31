package com.samsara.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "PostPicture")
@TableGenerator(name = "tabPostPicture", initialValue = 0, allocationSize = 1)
public class PostPicture implements Serializable{
	private static final long serialVersionUID = 7111304568345341340L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabPostPicture")
	@Column(name = "id", updatable = false, nullable = false)
	private Long  id;

	private boolean mainPic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMainPic() {
		return mainPic;
	}

	public void setMainPic(boolean mainPic) {
		this.mainPic = mainPic;
	}

}
