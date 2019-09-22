package my.finilogy.crawlertest.persistance;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

  private long id;
  private String name;
  private String url;
  private BigDecimal price;
  private String details;
  private String extraInformation;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  @Column(name = "name", length = 150)
  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Column(name = "URL")
  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  @Column(name = "price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(final BigDecimal price) {
    this.price = price;
  }

  @Column(name = "details")
  public String getDetails() {
    return details;
  }

  public void setDetails(final String details) {
    this.details = details;
  }

  @Column(name = "extra_inf")
  public String getExtraInformation() {
    return extraInformation;
  }

  public void setExtraInformation(final String extraInformation) {
    this.extraInformation = extraInformation;
  }

  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ProductEntity that = (ProductEntity) o;
    return url.equals(that.url);
  }

  @Override public int hashCode() {
    return Objects.hash(url);
  }
}
