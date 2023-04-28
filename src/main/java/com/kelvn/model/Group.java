package com.kelvn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
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
@Audited
public class Group extends BaseModel {

  @Column(name = "name")
  @NotNull
  private String name;

  @NotAudited
  @OneToMany(
    mappedBy = "group",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  private List<Account> accounts;

  public void updateRelations(){
    accounts.forEach(account -> account.setGroup(this));
  }

}
