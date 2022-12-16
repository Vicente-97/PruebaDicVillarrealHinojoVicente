package pruebaDic.DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;



import pruebaDic.model.Train;
import pruebaDic.model.Travels;

public class DaoTravels {

	private StandardServiceRegistry sr;
	private SessionFactory sf;
	private Session session;

	public DaoTravels() {
		this.sr = new StandardServiceRegistryBuilder().configure().build();
		this.sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		this.session = sf.openSession();
	}

	public Session getSession() {
		return session;
	}
	
	public boolean add(Travels travels) {
		boolean result = false;
		try {

			session.getTransaction().begin();
			session.saveOrUpdate(travels);
			session.getTransaction().commit();
			result = true;
		} catch (Exception g) {
			System.out.println("Error");
			session.getTransaction().rollback();
		}
		return result;
	}
	
	public ArrayList<Travels> getListPurchase(String user) {
		   
	   
		Query<Travels> query = session.createNativeQuery("SELECT p FROM pruebaDic.model.Travels p WHERE user='" + user+"'",Travels.class);
		ArrayList<Travels> purchase = (ArrayList<Travels>) query.getResultList();
		return purchase;
	}

}
