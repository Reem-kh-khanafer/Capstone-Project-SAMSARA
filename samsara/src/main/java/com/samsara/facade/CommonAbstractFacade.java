/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsara.facade;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class CommonAbstractFacade<T> {
	public Class<T> entityClass;

	public CommonAbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@PersistenceContext(unitName = "samsara-persistence-unit")
	protected EntityManager em;

	public T save(T entity) {

		beforeSave(entity);
		entity = em.merge(entity);
		afterSave(entity);
		return entity;
	}

	public void beforeSave(T entity) {

	}

	public void afterSave(T entity) {

	}

	public List<T> save(List<T> entities) {
		if (entities == null)
			return entities;

		for (int i = 0; i < entities.size(); i++) {
			entities.set(i, save(entities.get(i)));
		}

		return entities;
	}
	
	public Set<T> save(Set<T> entities) {
		if (entities == null)
			return entities;
		
		Set<T> newEntities = new HashSet<>() ; 
		for (Iterator<T> it = entities.iterator(); it.hasNext(); ) {
	        T f = it.next();
	        f =  save(f);
	        newEntities.add(f);
	       
	    }

		return newEntities;
	}

	public void remove(T entity) {
		if (beforeRemove(entity)){
			em.remove(em.merge(entity));
			afterRemove(entity);
		}
		
	}
	
	public boolean beforeRemove(T entity) {
		return true;
	}

	public void afterRemove(T entity) {
		
	}

	public void remove(T[] entity) {
		for (T t : entity) {
			remove(t);
		}

	}

	public T find(Object id) {
		return em.find(entityClass, id);
	}

	public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery(namedQueryName);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		for (Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = em.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(em.getCriteriaBuilder().count(rt));
		javax.persistence.Query q = em.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	protected T getSingleResult(TypedQuery<T> typedQuery) {

		List<T> t = typedQuery.getResultList();
		if (t == null || t.size() == 0)
			throw new EntityNotFoundException();
		return t.get(0);
	}

	public T createNewInstance() throws InstantiationException, IllegalAccessException {

		return entityClass.newInstance();
	}

	public void clearFromCache(Object id) {
		em.getEntityManagerFactory().getCache().evict(entityClass, id);
	}

}
