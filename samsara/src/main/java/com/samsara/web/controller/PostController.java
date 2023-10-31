package com.samsara.web.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

import com.samsara.facade.PostFacade;
import com.samsara.model.Post;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PostController implements Serializable {

	private static final long serialVersionUID = 427455222035011659L;
	
	private List<Post> apartments;

	@Inject
	private PostFacade apartmentFacade;
	
	@PostConstruct
	public void init() {
		apartments = apartmentFacade.findAll();
	}

	public List<Post> getApartments() {
		return apartments;
	}


	public void setApartments(List<Post> apartments) {
		this.apartments = apartments;
	}
}
