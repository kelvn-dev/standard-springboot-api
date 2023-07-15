package com.kelvn.model.Qmodel;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.kelvn.model.BaseModel;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import javax.annotation.processing.Generated;

/** QBaseModel is a Querydsl query type for BaseModel */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseModel extends EntityPathBase<BaseModel> {

  private static final long serialVersionUID = -2091754462L;

  public static final QBaseModel baseModel = new QBaseModel("baseModel");

  public final DateTimePath<java.time.LocalDateTime> createdAt =
      createDateTime("createdAt", java.time.LocalDateTime.class);

  public final StringPath createdBy = createString("createdBy");

  public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

  public final DateTimePath<java.time.LocalDateTime> updatedAt =
      createDateTime("updatedAt", java.time.LocalDateTime.class);

  public final StringPath updatedBy = createString("updatedBy");

  public QBaseModel(String variable) {
    super(BaseModel.class, forVariable(variable));
  }

  public QBaseModel(Path<? extends BaseModel> path) {
    super(path.getType(), path.getMetadata());
  }

  public QBaseModel(PathMetadata metadata) {
    super(BaseModel.class, metadata);
  }
}
