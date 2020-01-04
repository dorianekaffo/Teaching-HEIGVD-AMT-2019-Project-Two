package ch.heigvd.amt.p2.endpoints.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Enrollment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-02T04:27:12.447Z")

public class Enrollment   {
  @JsonProperty("person")
  private Student person = null;

  @JsonProperty("group")
  private Course group = null;

  public Enrollment person(Student person) {
    this.person = person;
    return this;
  }

  /**
   * Get person
   * @return person
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Student getPerson() {
    return person;
  }

  public void setPerson(Student person) {
    this.person = person;
  }

  public Enrollment group(Course group) {
    this.group = group;
    return this;
  }

  /**
   * Get group
   * @return group
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Course getGroup() {
    return group;
  }

  public void setGroup(Course group) {
    this.group = group;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Enrollment enrollment = (Enrollment) o;
    return Objects.equals(this.person, enrollment.person) &&
        Objects.equals(this.group, enrollment.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, group);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Enrollment {\n");
    
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

