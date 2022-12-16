package pruebaDic.DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;



import pruebaDic.model.User;

public class DaoUser {

	private StandardServiceRegistry sr;
	private SessionFactory sf;
	private Session session;

	public DaoUser() {
		this.sr = new StandardServiceRegistryBuilder().configure().build();
		this.sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		this.session = sf.openSession();
	}

	public Session getSession() {
		return session;
	}

	public User get(String username) {
		User user = null;
		try {
			user = session.get(User.class, username);
		} catch (Exception e) {
			System.out.println("Error");
		}
		return user;
	}

	public ArrayList<User> getList() {
		ArrayList<User> lista= null;
		try {
			String hql = "SELECT * FROM user u";
			Query<User> query = session.createNativeQuery(hql, User.class);
			lista =  (ArrayList<User>) query.getResultList();
		} catch (Exception f) {
			System.out.println("Error");
		}
		return lista;
	}

	public boolean add(User user) {
		boolean result = false;
		try {

			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
			result = true;
		} catch (Exception g) {
			System.out.println("Error");
		}
		return result;
	}

	public void delete(User user)  {
		try {
			session.getTransaction().begin();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
	}

	public boolean update(User user) {
		boolean result = false;
		try {

			session.getTransaction().begin();
			session.update(user);
			session.getTransaction().commit();
			result = true;
		} catch (Exception g) {
			System.out.println("Error");
		}
		return result;
	}
	
	public boolean userIsValid(String username, String password) {
		
	    boolean valid = false;
	    
		Query<User> query = session.createQuery("SELECT p FROM pruebaDic.model.User p WHERE username='" + username + "'and password='"+password+"'",User.class);
		if(!query.getResultList().isEmpty()) {
		    valid = true;
		
		}
		return valid;
	}
	
}
