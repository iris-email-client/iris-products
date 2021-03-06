package
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.relational;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.FolderContent;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.IrisFolder;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.AddressBookEntry;
/*** added by dPersistenceRelational* modified by dAddressBookRelational
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		classList = new ArrayList<Class>();
		configureClasses();
		try {
			Configuration configuration = new Configuration();
			for(Class clazz : classList) {
				configuration.addAnnotatedClass(clazz);
			}
			configuration.configure();
			Properties properties = configuration.getProperties();
			ServiceRegistry serviceRegistry = new
			StandardServiceRegistryBuilder().applySettings(properties).build();
			return configuration.buildSessionFactory(serviceRegistry);
		}
		catch(Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}
	private static List<Class> classList;
	/*** modified by dAddressBookRelational
	 */
	public static void configureClasses() {
		classList.add(AddressBookEntry.class);
		configureClasses_original0();
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void shutdown() {
		getSessionFactory().close();
	}
	/*** modified by dAddressBookRelational
	 */
	public static void configureClasses_original0() {
		classList.add(FolderContent.class);
		classList.add(EmailMessage.class);
		classList.add(IrisFolder.class);
	}
}