package my.finilogy.crawlertest.persistance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Jafar Mirzaei (jm.csh2009@gmail.com)
 * @version 1.0 2018.0519
 * @since 1.0
 */

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  private Long id;
  private Long version;

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  @Version
  public Long getVersion() {
    return version;
  }

  public void setVersion(final Long version) {
    this.version = version;
  }
}