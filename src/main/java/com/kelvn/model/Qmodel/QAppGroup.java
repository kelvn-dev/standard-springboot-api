package com.kelvn.model.Qmodel;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.kelvn.model.Account;
import com.kelvn.model.AppGroup;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QAppGroup is a Querydsl query type for AppGroup */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppGroup extends EntityPathBase<AppGroup> {

  private static final long serialVersionUID = -1633670572L;

  public static final QAppGroup appGroup = new QAppGroup("appGroup");

  public final QBaseModel _super = new QBaseModel(this);

  public final ListPath<Account, QAccount> accounts =
      this.<Account, QAccount>createList(
          "accounts", Account.class, QAccount.class, PathInits.DIRECT2);

  // inherited
  public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

  // inherited
  public final StringPath createdBy = _super.createdBy;

  // inherited
  public final ComparablePath<java.util.UUID> id = _super.id;

  public final StringPath name = createString("name");

  // inherited
  public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

  // inherited
  public final StringPath updatedBy = _super.updatedBy;

  public QAppGroup(String variable) {
    super(AppGroup.class, forVariable(variable));
  }

  public QAppGroup(Path<? extends AppGroup> path) {
    super(path.getType(), path.getMetadata());
  }

  public QAppGroup(PathMetadata metadata) {
    super(AppGroup.class, metadata);
  }
}
