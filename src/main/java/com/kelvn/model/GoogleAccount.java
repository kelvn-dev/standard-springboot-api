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
    name = "google_account",
    indexes = {@Index(columnList = "google_account_id")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
// @Audited
// @SQLDelete(sql = "UPDATE account SET is_deleted = TRUE WHERE id = ?")
// @Where(clause = "is_deleted = false")
public class GoogleAccount extends BaseModel {

  @Column(name = "google_account_id", unique = true)
  @NotNull private String sub;

  @Column(name = "name")
  @NotNull private String name;

  @Column(name = "email")
  @NotNull private String email;

  @Column(name = "email_verified")
  @NotNull private String email_verified;

  @Column(name = "family_name")
  @NotNull private String family_name;

  @Column(name = "given_name")
  @NotNull private String given_name;

  @Column(name = "picture_url")
  @NotNull private String picture;

  @OneToOne(
      mappedBy = "googleAccount",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Account account;
}
