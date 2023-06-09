package com.kelvn.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass // ensure that won't have a separate representation as table of the extending class
@EntityListeners(AuditingEntityListener.class)
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

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedBy
  @Column(name = "updated_by")
  private String updatedBy;

//  @Column(name = "is_deleted")
//  private boolean isDeleted;

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

}
