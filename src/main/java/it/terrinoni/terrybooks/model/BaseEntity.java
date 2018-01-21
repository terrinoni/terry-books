package it.terrinoni.terrybooks.model;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:55.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Base Entity model class, used for auditing purposes; this class is extended by all the other
 * model mapping classes in this package.
 */
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Field("created_at")
  private Date createdAt;

  @Field("last_modified_at")
  private Date lastModifiedAt;

  BaseEntity() {
    createdAt = new Date();
    lastModifiedAt = null;
  }

  public BaseEntity(Date createdAt, Date lastModifiedAt) {
    this.createdAt = createdAt;
    this.lastModifiedAt = lastModifiedAt;
  }

  @JsonProperty("created_at")
  @ApiModelProperty(value = "Audit field object creation timestamp")
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("last_modified_at")
  @ApiModelProperty(value = "Audit field object last modification timestamp")
  public Date getLastModifiedAt() {
    return lastModifiedAt;
  }

  public void setLastModifiedAt(Date lastModifiedAt) {
    this.lastModifiedAt = lastModifiedAt;
  }
}