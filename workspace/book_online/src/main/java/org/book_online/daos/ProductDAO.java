package org.book_online.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.book_online.models.Product;
import org.springframework.stereotype.Repository;

@Repository
@Transactional 
public class ProductDAO {
	
	@PersistenceContext 
	private EntityManager manager;
	
	public void save(Product product){ 
		manager.persist(product); 
   }

	public List<Product> list(){ 
		return manager.createQuery("select distinct(p) from "
				+ "Product p join fetch p.prices",Product.class)
				.getResultList(); 
   }

}
