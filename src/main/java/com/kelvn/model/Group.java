package com.kelvn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Group extends BaseModel {

  @Column(name = "name")
  @NotNull
  private String name;

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
