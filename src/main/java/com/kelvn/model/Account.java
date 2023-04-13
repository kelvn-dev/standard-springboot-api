package com.kelvn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Account extends BaseModel {

  @Column(name = "username")
  @NotNull
  private String username;

  @Column(name = "email")
  @NotNull
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name="group_id", columnDefinition = "BINARY(16)")
  private UUID groupId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  @JsonIgnoreProperties("accounts")
  private Group group;
}
