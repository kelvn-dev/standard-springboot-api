package com.kelvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(
    name = "meta_account",
    indexes = {@Index(columnList = "meta_account_id")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
// @Audited
// @SQLDelete(sql = "UPDATE account SET is_deleted = TRUE WHERE id = ?")
// @Where(clause = "is_deleted = false")
public class MetaAccount extends BaseModel {

  @Column(name = "meta_account_id", unique = true)
  @NotNull private String metaAccountId;

  @Column(name = "name")
  @NotNull private String name;

  @Column(name = "email")
  @NotNull private String email;

  @Column(name = "first_name")
  @NotNull private String first_name;

  @Column(name = "last_name")
  @NotNull private String last_name;

  @OneToOne(
      mappedBy = "metaAccount",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Account account;
}
