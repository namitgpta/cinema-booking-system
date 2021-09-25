package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the MovieModel type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "MovieModels", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class MovieModel implements Model {
  public static final QueryField ID = field("MovieModel", "id");
  public static final QueryField MOVIE_ID = field("MovieModel", "movieId");
  public static final QueryField TITLE = field("MovieModel", "title");
  public static final QueryField DURATION = field("MovieModel", "duration");
  public static final QueryField RATING = field("MovieModel", "rating");
  public static final QueryField RELEASE_DATE = field("MovieModel", "releaseDate");
  public static final QueryField PATH_NAME = field("MovieModel", "pathName");
  public static final QueryField EXTERNAL_LINK = field("MovieModel", "externalLink");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int", isRequired = true) Integer movieId;
  private final @ModelField(targetType="String") String title;
  private final @ModelField(targetType="String") String duration;
  private final @ModelField(targetType="String") String rating;
  private final @ModelField(targetType="String") String releaseDate;
  private final @ModelField(targetType="String") String pathName;
  private final @ModelField(targetType="String") String externalLink;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Integer getMovieId() {
      return movieId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getDuration() {
      return duration;
  }
  
  public String getRating() {
      return rating;
  }
  
  public String getReleaseDate() {
      return releaseDate;
  }
  
  public String getPathName() {
      return pathName;
  }
  
  public String getExternalLink() {
      return externalLink;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private MovieModel(String id, Integer movieId, String title, String duration, String rating, String releaseDate, String pathName, String externalLink) {
    this.id = id;
    this.movieId = movieId;
    this.title = title;
    this.duration = duration;
    this.rating = rating;
    this.releaseDate = releaseDate;
    this.pathName = pathName;
    this.externalLink = externalLink;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      MovieModel movieModel = (MovieModel) obj;
      return ObjectsCompat.equals(getId(), movieModel.getId()) &&
              ObjectsCompat.equals(getMovieId(), movieModel.getMovieId()) &&
              ObjectsCompat.equals(getTitle(), movieModel.getTitle()) &&
              ObjectsCompat.equals(getDuration(), movieModel.getDuration()) &&
              ObjectsCompat.equals(getRating(), movieModel.getRating()) &&
              ObjectsCompat.equals(getReleaseDate(), movieModel.getReleaseDate()) &&
              ObjectsCompat.equals(getPathName(), movieModel.getPathName()) &&
              ObjectsCompat.equals(getExternalLink(), movieModel.getExternalLink()) &&
              ObjectsCompat.equals(getCreatedAt(), movieModel.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), movieModel.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getMovieId())
      .append(getTitle())
      .append(getDuration())
      .append(getRating())
      .append(getReleaseDate())
      .append(getPathName())
      .append(getExternalLink())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("MovieModel {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("movieId=" + String.valueOf(getMovieId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("duration=" + String.valueOf(getDuration()) + ", ")
      .append("rating=" + String.valueOf(getRating()) + ", ")
      .append("releaseDate=" + String.valueOf(getReleaseDate()) + ", ")
      .append("pathName=" + String.valueOf(getPathName()) + ", ")
      .append("externalLink=" + String.valueOf(getExternalLink()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static MovieIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static MovieModel justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new MovieModel(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      movieId,
      title,
      duration,
      rating,
      releaseDate,
      pathName,
      externalLink);
  }
  public interface MovieIdStep {
    BuildStep movieId(Integer movieId);
  }
  

  public interface BuildStep {
    MovieModel build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep title(String title);
    BuildStep duration(String duration);
    BuildStep rating(String rating);
    BuildStep releaseDate(String releaseDate);
    BuildStep pathName(String pathName);
    BuildStep externalLink(String externalLink);
  }
  

  public static class Builder implements MovieIdStep, BuildStep {
    private String id;
    private Integer movieId;
    private String title;
    private String duration;
    private String rating;
    private String releaseDate;
    private String pathName;
    private String externalLink;
    @Override
     public MovieModel build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new MovieModel(
          id,
          movieId,
          title,
          duration,
          rating,
          releaseDate,
          pathName,
          externalLink);
    }
    
    @Override
     public BuildStep movieId(Integer movieId) {
        Objects.requireNonNull(movieId);
        this.movieId = movieId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep duration(String duration) {
        this.duration = duration;
        return this;
    }
    
    @Override
     public BuildStep rating(String rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
     public BuildStep releaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
    
    @Override
     public BuildStep pathName(String pathName) {
        this.pathName = pathName;
        return this;
    }
    
    @Override
     public BuildStep externalLink(String externalLink) {
        this.externalLink = externalLink;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Integer movieId, String title, String duration, String rating, String releaseDate, String pathName, String externalLink) {
      super.id(id);
      super.movieId(movieId)
        .title(title)
        .duration(duration)
        .rating(rating)
        .releaseDate(releaseDate)
        .pathName(pathName)
        .externalLink(externalLink);
    }
    
    @Override
     public CopyOfBuilder movieId(Integer movieId) {
      return (CopyOfBuilder) super.movieId(movieId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder duration(String duration) {
      return (CopyOfBuilder) super.duration(duration);
    }
    
    @Override
     public CopyOfBuilder rating(String rating) {
      return (CopyOfBuilder) super.rating(rating);
    }
    
    @Override
     public CopyOfBuilder releaseDate(String releaseDate) {
      return (CopyOfBuilder) super.releaseDate(releaseDate);
    }
    
    @Override
     public CopyOfBuilder pathName(String pathName) {
      return (CopyOfBuilder) super.pathName(pathName);
    }
    
    @Override
     public CopyOfBuilder externalLink(String externalLink) {
      return (CopyOfBuilder) super.externalLink(externalLink);
    }
  }
  
}
