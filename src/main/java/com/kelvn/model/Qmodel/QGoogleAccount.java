package com.kelvn.model.Qmodel;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.kelvn.model.GoogleAccount;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QGoogleAccount is a Querydsl query type for GoogleAccount */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoogleAccount extends EntityPathBase<GoogleAccount> {

  private static final long serialVersionUID = -331806210L;

  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QGoogleAccount googleAccount = new QGoogleAccount("googleAccount");

  public final QBaseModel _super = new QBaseModel(this);

  public final QAccount account;

  // inherited
  public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

  // inherited
  public final StringPath createdBy = _super.createdBy;

  public final StringPath email = createString("email");

  public final StringPath email_verified = createString("email_verified");

  public final StringPath family_name = createString("family_name");

  public final StringPath given_name = createString("given_name");

  // inherited
  public final ComparablePath<java.util.UUID> id = _super.id;

  public final StringPath name = createString("name");

  public final StringPath picture = createString("picture");

  public final StringPath sub = createString("sub");

  // inherited
  public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

  // inherited
  public final StringPath updatedBy = _super.updatedBy;

  public QGoogleAccount(String variable) {
    this(GoogleAccount.class, forVariable(variable), INITS);
  }

  public QGoogleAccount(Path<? extends GoogleAccount> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QGoogleAccount(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QGoogleAccount(PathMetadata metadata, PathInits inits) {
    this(GoogleAccount.class, metadata, inits);
  }

  public QGoogleAccount(
      Class<? extends GoogleAccount> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.account =
        inits.isInitialized("account")
            ? new QAccount(forProperty("account"), inits.get("account"))
            : null;
  }
}
