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

/** This is an auto generated class representing the UsersModel type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UsersModels", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class UsersModel implements Model {
  public static final QueryField ID = field("UsersModel", "id");
  public static final QueryField FIREBASE_ID = field("UsersModel", "firebaseId");
  public static final QueryField LOCATION = field("UsersModel", "location");
  public static final QueryField EMAIL = field("UsersModel", "email");
  public static final QueryField PHONE = field("UsersModel", "phone");
  public static final QueryField NAME_OF_USER = field("UsersModel", "nameOfUser");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String firebaseId;
  private final @ModelField(targetType="String") String location;
  private final @ModelField(targetType="String") String email;
  private final @ModelField(targetType="String") String phone;
  private final @ModelField(targetType="String") String nameOfUser;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getFirebaseId() {
      return firebaseId;
  }
  
  public String getLocation() {
      return location;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public String getNameOfUser() {
      return nameOfUser;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UsersModel(String id, String firebaseId, String location, String email, String phone, String nameOfUser) {
    this.id = id;
    this.firebaseId = firebaseId;
    this.location = location;
    this.email = email;
    this.phone = phone;
    this.nameOfUser = nameOfUser;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UsersModel usersModel = (UsersModel) obj;
      return ObjectsCompat.equals(getId(), usersModel.getId()) &&
              ObjectsCompat.equals(getFirebaseId(), usersModel.getFirebaseId()) &&
              ObjectsCompat.equals(getLocation(), usersModel.getLocation()) &&
              ObjectsCompat.equals(getEmail(), usersModel.getEmail()) &&
              ObjectsCompat.equals(getPhone(), usersModel.getPhone()) &&
              ObjectsCompat.equals(getNameOfUser(), usersModel.getNameOfUser()) &&
              ObjectsCompat.equals(getCreatedAt(), usersModel.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), usersModel.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFirebaseId())
      .append(getLocation())
      .append(getEmail())
      .append(getPhone())
      .append(getNameOfUser())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UsersModel {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("firebaseId=" + String.valueOf(getFirebaseId()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("nameOfUser=" + String.valueOf(getNameOfUser()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static FirebaseIdStep builder() {
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
  public static UsersModel justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new UsersModel(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      firebaseId,
      location,
      email,
      phone,
      nameOfUser);
  }
  public interface FirebaseIdStep {
    BuildStep firebaseId(String firebaseId);
  }
  

  public interface BuildStep {
    UsersModel build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep location(String location);
    BuildStep email(String email);
    BuildStep phone(String phone);
    BuildStep nameOfUser(String nameOfUser);
  }
  

  public static class Builder implements FirebaseIdStep, BuildStep {
    private String id;
    private String firebaseId;
    private String location;
    private String email;
    private String phone;
    private String nameOfUser;
    @Override
     public UsersModel build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UsersModel(
          id,
          firebaseId,
          location,
          email,
          phone,
          nameOfUser);
    }
    
    @Override
     public BuildStep firebaseId(String firebaseId) {
        Objects.requireNonNull(firebaseId);
        this.firebaseId = firebaseId;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.location = location;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    @Override
     public BuildStep nameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
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
    private CopyOfBuilder(String id, String firebaseId, String location, String email, String phone, String nameOfUser) {
      super.id(id);
      super.firebaseId(firebaseId)
        .location(location)
        .email(email)
        .phone(phone)
        .nameOfUser(nameOfUser);
    }
    
    @Override
     public CopyOfBuilder firebaseId(String firebaseId) {
      return (CopyOfBuilder) super.firebaseId(firebaseId);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
    
    @Override
     public CopyOfBuilder nameOfUser(String nameOfUser) {
      return (CopyOfBuilder) super.nameOfUser(nameOfUser);
    }
  }
  
}
