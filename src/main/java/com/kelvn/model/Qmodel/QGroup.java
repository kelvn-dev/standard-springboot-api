package com.kelvn.model.Qmodel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kelvn.model.Account;
import com.kelvn.model.Group;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;
import javax.annotation.processing.Generated;

/** QGroup is a Querydsl query type for Group */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroup extends EntityPathBase<Group> {

  private static final long serialVersionUID = -1506940727L;

  public static final QGroup group = new QGroup("group1");

  public final QBaseModel _super = new QBaseModel(this);

  public final ListPath<Account, QAccount> accounts =
      this.<Account, QAccount>createList(
          "accounts", Account.class, QAccount.class, PathInits.DIRECT2);

  // inherited
  public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

  // inherited
  public final ComparablePath<java.util.UUID> id = _super.id;

  public final StringPath name = createString("name");

  // inherited
  public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

  public QGroup(String variable) {
    super(Group.class, forVariable(variable));
  }

  public QGroup(Path<? extends Group> path) {
    super(path.getType(), path.getMetadata());
  }

  public QGroup(PathMetadata metadata) {
    super(Group.class, metadata);
  }
}
