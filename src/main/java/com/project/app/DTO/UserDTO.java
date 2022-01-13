package com.project.app.DTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserDTO {

	private long id;

	@NotNull(message = "Le prenom doit etre obligatoire.")
	@Min(value = 4, message = "Le prenom doit etre superieur à 4 caractere.")
	@Min(value = 25, message = "Le prenom doit etre inferieur à 25 caractere.")
	private String firstname;

	@NotNull(message = "Le nom doit etre obligatoire.")
	@Min(value = 4, message = "Le nom doit etre superieur à 4 caractere.")
	@Min(value = 30, message = "Le nom doit etre inferieur à 30 caractere.")
	private String lastname;

	@NotNull(message = "Utilisateur doit etre obligatoire.")
	@Min(value = 10, message = "Utilisateur doit etre superieur à 10 caractere.")
	@Min(value = 50, message = "Utilisateur doit etre inferieur à 50 caractere.")
//	@UniqueElements(message = "Utilisateur deja existe.")

	private String username;

	@NotNull(message = "Email doit etre obligatoire.")
	@Email(message = "Vous devez respecter la format email.")
	@Min(value = 250, message = "Email doit etre inferieur à 250 caractere.")
//	@UniqueElements(message = "Email deja existe.")
	private String email;

	@NotNull(message = "Mot de passe doit etre obligatoire.")
	@Min(value = 8, message = "Mot de passe doit etre superieur à 8 caractere.")
	@Min(value = 100, message = "Mot de passe doit etre inferieur à 100 caractere.")
	private String password;

	@NotNull(message = "Le telephone doit etre obligatoire.")
	private String phone;

	@NotNull(message = "La ville doit etre obligatoire.")
	private String city;

	private Boolean enabled;

	private Set<RoleDTO> roles = new HashSet<>();

	private List<OrderDTO> orders;

	public UserDTO() {}

	public UserDTO(long id, String firstname, String lastname, String username, String email, String password,
			String phone, String city, Boolean enabled, Set<RoleDTO> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.city = city;
		this.enabled = enabled;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

}
