package irisdeltaj.completerelational.br.unb.cic.iris.persistence.relational;

import irisdeltaj.completerelational.br.unb.cic.iris.core.exception.DBException;
import irisdeltaj.completerelational.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completerelational.br.unb.cic.iris.core.model.Category;
import irisdeltaj.completerelational.br.unb.cic.iris.persistence.ICategoryDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/***
 * added by dCategoryRelational
 */
public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {
	private static CategoryDAO instance;

	private CategoryDAO() {
	}

	public static CategoryDAO instance() throws EmailException {
		if (instance == null) {
			instance = new CategoryDAO();
			ensureIsCreated(Category.FORUMS);
			ensureIsCreated(Category.PRIMARY);
			ensureIsCreated(Category.PROMOTIONS);
			ensureIsCreated(Category.SOCIAL);
			ensureIsCreated(Category.UPDATES);
		}
		return instance;
	}
	
	private static void ensureIsCreated(String category) throws EmailException {
		Category folder = instance.findByName(category);
		if (folder == null) {
			instance.saveOrUpdate(new Category(category));
			System.out.printf("%s category created.\n", category);
		}
	}

	public Category findByName(String categoryName) throws DBException {
		try {
			startSession(false);
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("name", categoryName));
			return (Category) criteria.uniqueResult();
		} catch (Exception e) {
			handleException(new DBException("Category not found: " + categoryName, e));
		} finally {
			closeSession();
		}
		return null;
	}
}