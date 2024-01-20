package com.exam.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shows")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required!")
	private String title;
	
	@NotBlank(message = "Network  is required!")
	@Size(min = 2, message = "Network must be at least 2 characters long")
	private String network;

	@NotBlank(message = "Description is required!")
	@Size(min = 10, message = "Description must be at least 10 characters long")
	private String description;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	//relation 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "show_id")
	private User owner;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_shows", joinColumns = @JoinColumn(name = "show_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
    @Column(updatable=false)
    @OneToMany(mappedBy="show",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews;
    
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	private Float rating;
	public Show() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}

	
	
}
