package com.kelvn.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "facebook_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Audited
public class FBAccount extends BaseModel {
  private String FBAccountId;
  private String name;
  private String email;
  private String first_name;
  private String last_name;
}
