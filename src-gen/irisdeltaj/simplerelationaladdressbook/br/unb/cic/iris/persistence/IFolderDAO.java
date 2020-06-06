package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence;

import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.IrisFolder;
/*** added by dBasePersistence
 */
public interface IFolderDAO {
	public IrisFolder findByName(String folderName) throws EmailException;
	public IrisFolder findById(String id) throws EmailException;
}