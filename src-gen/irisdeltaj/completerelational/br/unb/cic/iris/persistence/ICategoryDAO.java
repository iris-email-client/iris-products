package irisdeltaj.completerelational.br.unb.cic.iris.persistence;

import irisdeltaj.completerelational.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completerelational.br.unb.cic.iris.core.model.Category;

/***
 * added by dCategoryBase
 */
public interface ICategoryDAO {
	public Category findById(String id) throws EmailException;

	public Category findByName(String name) throws EmailException;

	public java.util.List<Category> findAll() throws EmailException;
}