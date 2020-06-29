package irisdeltaj.completerelational.br.unb.cic.iris.core;

import irisdeltaj.completerelational.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completerelational.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.completerelational.br.unb.cic.iris.core.model.Category;
import irisdeltaj.completerelational.br.unb.cic.iris.persistence.ICategoryDAO;
import irisdeltaj.completerelational.br.unb.cic.iris.persistence.relational.CategoryDAO;
import irisdeltaj.completerelational.br.unb.cic.iris.persistence.relational.EmailDAO;

/***
 * added by dCategoryBase* modified by dCategoryRelational
 */
public final class CategoryManager {
	private static final CategoryManager instance = new CategoryManager();

	private CategoryManager() {
	}

	public static CategoryManager instance() {
		return instance;
	}

	/***
	 * modified by dCategoryRelational
	 */
	public ICategoryDAO getCategoryDAO() throws EmailException {
		return CategoryDAO.instance();
	}

	public Category findById(String id) throws EmailException {
		return getCategoryDAO().findById(id);
	}

	public Category findByName(String name) throws EmailException {
		return getCategoryDAO().findByName(name);
	}

	public java.util.List<Category> findAll() throws EmailException {
		return getCategoryDAO().findAll();
	}

	public java.util.List<EmailMessage> listMessagesByCategory(String category) throws EmailException {
		return EmailDAO.instance().listMessagesByCategory(category);
	}
}