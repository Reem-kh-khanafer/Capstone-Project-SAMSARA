package com.samsara.facade;

import javax.ejb.Stateless;
import com.samsara.model.Post;


@Stateless
public class PostFacade extends CommonAbstractFacade<Post> {
	
	public PostFacade() {
		super(Post.class);
	}
	
	public PostFacade(Class<Post> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

}
