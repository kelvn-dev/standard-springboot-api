package com.kelvn.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "meta_account", indexes = {@Index(columnList = "meta_account_id")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Audited
public class MetaAccount extends BaseModel {

  @Column(name = "meta_account_id", unique = true)
  @NotNull
  private String metaAccountId;

  @Column(name = "name")
  @NotNull
  private String name;

  @Column(name = "email")
  @NotNull
  private String email;

  @Column(name = "first_name")
  @NotNull
  private String first_name;

  @Column(name = "last_name")
  @NotNull
  private String last_name;

  @NotAudited
  @OneToOne(
    mappedBy = "metaAccount",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true
  )
  private Account account;
}
