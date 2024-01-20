package com.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.Review;
import com.exam.models.Show;
import com.exam.models.User;
import com.exam.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	public List<Review> allReviews() {
		return reviewRepo.findAll();
	}

	public List<Review> findByRate(Show show) {
		return reviewRepo.findByShowIs(show);
	}

	public boolean hasUserReviewedShow(User user, Show show) {
		List<Review> userReviews = findByUser(user);
		for (Review review : userReviews) {
			if (review.getShow().equals(show)) {
				// User has already reviewed this show
				return true;
			}
		}
		// User has not reviewed this show
		return false;
	}

	private List<Review> findByUser(User user) {
		return reviewRepo.findByUser(user);
	}

	public Review updateReview(Review review) {
		return reviewRepo.save(review);
	}

	public Review addReview(Review review) {
		return reviewRepo.save(review);
	}

	public void deleteReview(Review review) {
		reviewRepo.delete(review);
	}

	public Review findById(Long id) {
		Optional<Review> optionalReview = reviewRepo.findById(id);
		if (optionalReview.isPresent()) {
			return optionalReview.get();
		} else {
			return null;
		}
	}
}
