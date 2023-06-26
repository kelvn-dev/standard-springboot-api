package com.kelvn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@DynamicUpdate
// @Audited
// @SQLDelete(sql = "UPDATE account SET is_deleted = TRUE WHERE id = ?")
// @Where(clause = "is_deleted = false")
public class Account extends BaseModel implements UserDetails {

  @Column(name = "username")
  @NotNull private String username;

  @Column(name = "email")
  @NotNull private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "birth_year")
  private Integer birthYear;

  @Column(name = "group_id", columnDefinition = "BINARY(16)")
  private UUID groupId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  @JsonIgnoreProperties("accounts")
  private Group group;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "meta_account_id", referencedColumnName = "id")
  private MetaAccount metaAccount;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "google_account_id", referencedColumnName = "id")
  private GoogleAccount googleAccount;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new HashSet<>();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
