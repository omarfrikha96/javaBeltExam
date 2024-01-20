package com.exam.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="reviews")
public class Review {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotNull
	@Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be greater than 5")
	private float rating;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    //Relation 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_show_id")
    private Show show;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    public Review() {}
    

	public Review(Float rating,User user, Show show) {

		this.rating = rating;
		this.user = user;
		this.show = show;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

}
