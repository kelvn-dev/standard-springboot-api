package com.kelvn.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass // ensure that won't have a separate representation as table of the extending class
public abstract class BaseModel implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(
    name = "id",
    columnDefinition = "BINARY(16)", // make the value of UUID more readable
    updatable = false,
    nullable = false
  )
  private UUID id;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof BaseModel)) return false;
    BaseModel that = (BaseModel) object;
    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

//  private String createdBy;
//  private String updatedBy;

}
