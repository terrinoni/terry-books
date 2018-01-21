package it.terrinoni.terrybooks.model;

/*
 * Created by Marco Terrinoni, on 21/01/2018 - 01:53.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Book model class.
 */
@Document(collection = "book")
public class Book extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String isbn;

  @Field("title")
  private String title;

  @Indexed
  @Field("author")
  private String author;

  @Field("publish_date")
  private Date publishDate;

  @Field("summary")
  private String summary;

  @Field("note")
  private String note;

  public Book() {
  }

  public Book(String isbn, String title, String author, Date publishDate,
      String summary, String note) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publishDate = publishDate;
    this.summary = summary;
    this.note = note;
  }

  @JsonProperty("isbn")
  @ApiModelProperty(value = "Book unique identifier")
  public String getIsbn() {
    return isbn;
  }

  @JsonProperty("title")
  @ApiModelProperty(value = "Title of the book")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty("author")
  @ApiModelProperty(value = "Comma-separated authors of the book")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @JsonProperty("publish_date")
  @ApiModelProperty(value = "Publish date of the book")
  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  @JsonProperty("summary")
  @ApiModelProperty(value = "Abstract of the book")
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @JsonProperty("note")
  @ApiModelProperty(value = "Internal notes")
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Book{" +
        "isbn='" + isbn + '\'' +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", publishDate=" + publishDate +
        ", summary='" + summary + '\'' +
        ", note='" + note + '\'' +
        '}';
  }
}