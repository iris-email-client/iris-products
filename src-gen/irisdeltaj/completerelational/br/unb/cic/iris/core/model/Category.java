package irisdeltaj.completerelational.br.unb.cic.iris.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_CATEGORY")
public class Category {
	public static final String PRIMARY = "PRIMARY";
	public static final String SOCIAL = "SOCIAL";
	public static final String PROMOTIONS = "PROMOTIONS";
	public static final String UPDATES = "UPDATES";
	public static final String FORUMS = "FORUMS";
	
	@Id
	@GeneratedValue(generator = "uuid")
	@Column(name = "CATEGORY_ID", nullable = false, updatable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(unique = true)
	private String name;

	public Category() {
		this(null);
	}

	public Category(String name) {
		this(null, name);
	}

	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}