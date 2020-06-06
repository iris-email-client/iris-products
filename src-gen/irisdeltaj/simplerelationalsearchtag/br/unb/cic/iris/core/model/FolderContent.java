package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
/*** added by dPersistenceRelational
 */
@Entity
@Table(name = "TB_FOLDER_CONTENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FolderContent {
	public FolderContent() {
		this(null);
	}
	public FolderContent(String id) {
		super();
		this.id = id;
	}
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}