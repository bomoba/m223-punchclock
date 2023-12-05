package ch.zli.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Member
@NamedQueries({
    @NamedQuery(name = "ApplicationUser.findByEmail", query = "SELECT m FROM ApplicationUser m WHERE m.email = :email")
})
public class ApplicationUser {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long memberID;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column
  private String firstName;

  public Long getId() {
    return memberID;
  }

  public void setId(Long memberID) {
    this.memberID = memberID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstname(String firstName) {
    this.firstName = firstName;
  }
}
