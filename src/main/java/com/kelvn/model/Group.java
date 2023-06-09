package com.kelvn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "app_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
//@Audited
//@SQLDelete(sql = "UPDATE app_group SET is_deleted = TRUE WHERE id = ?")
//@Where(clause = "is_deleted = false")
public class Group extends BaseModel {

  @Column(name = "name")
  @NotNull
  private String name;

  @NotAudited
  @OneToMany(
    mappedBy = "group",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true
  )
  private List<Account> accounts;

  public void updateRelations(){
    accounts.forEach(account -> account.setGroup(this));
  }

}
