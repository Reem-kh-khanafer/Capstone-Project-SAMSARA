package com.samsara.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;

import com.samsara.facade.PostFacade;
import com.samsara.model.Post;


@Named
@ViewScoped
public class PostCardController implements Serializable {

	private static final long serialVersionUID = 3888123706080734572L;

	private Post apartment;
	
	@Inject
	@Param(name = "id")
	private long id;
	
	@Inject
	private PostFacade apartmentFacade;
	
	@PostConstruct
	private void init() {
		if (id > 0) {
			apartment = apartmentFacade.find(id);
		} else {
			apartment = new Post();
		}
	}
	
	public void save() {
		try {
			apartment = apartmentFacade.save(apartment);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public Post getApartment() {
		return apartment;
	}

	public void setApartment(Post apartment) {
		this.apartment = apartment;
	}
}
