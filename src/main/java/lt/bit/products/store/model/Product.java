package lt.bit.products.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "store_products")
public class Product {

  @Id
  private Integer id;
  private String name;
  private String description;
  private LocalDate created;

  public Product(Integer id, String name, String description, LocalDate created) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.created = created;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }
}
