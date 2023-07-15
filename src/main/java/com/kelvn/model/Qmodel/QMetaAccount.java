package com.kelvn.model.Qmodel;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.kelvn.model.MetaAccount;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QMetaAccount is a Querydsl query type for MetaAccount */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMetaAccount extends EntityPathBase<MetaAccount> {

  private static final long serialVersionUID = -1802059982L;

  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QMetaAccount metaAccount = new QMetaAccount("metaAccount");

  public final QBaseModel _super = new QBaseModel(this);

  public final QAccount account;

  // inherited
  public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

  // inherited
  public final StringPath createdBy = _super.createdBy;

  public final StringPath email = createString("email");

  public final StringPath first_name = createString("first_name");

  // inherited
  public final ComparablePath<java.util.UUID> id = _super.id;

  public final StringPath last_name = createString("last_name");

  public final StringPath metaAccountId = createString("metaAccountId");

  public final StringPath name = createString("name");

  // inherited
  public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

  // inherited
  public final StringPath updatedBy = _super.updatedBy;

  public QMetaAccount(String variable) {
    this(MetaAccount.class, forVariable(variable), INITS);
  }

  public QMetaAccount(Path<? extends MetaAccount> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QMetaAccount(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QMetaAccount(PathMetadata metadata, PathInits inits) {
    this(MetaAccount.class, metadata, inits);
  }

  public QMetaAccount(Class<? extends MetaAccount> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.account =
        inits.isInitialized("account")
            ? new QAccount(forProperty("account"), inits.get("account"))
            : null;
  }
}
