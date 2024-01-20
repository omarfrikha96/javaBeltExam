package com.exam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.models.Review;
import com.exam.models.Show;
import com.exam.models.User;
import com.exam.services.ReviewService;
import com.exam.services.ShowService;
import com.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ShowController {
	@Autowired
	private ShowService showService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private HttpSession session;

	// read all shows
	@GetMapping("/shows")
	public String dashboard(Model model) {

		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		User user = userService.findById(userId);
		List<Show> all_shows= showService.allShows();
		model.addAttribute("user", user);
		model.addAttribute("shows",all_shows);
		
		return "dashboard.jsp";
	}

	// Create new show
	@GetMapping("/shows/new")
	public String newShow(@ModelAttribute("show") Show show, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");

		model.addAttribute("userId", userId);
		return "create_show.jsp";
	}

	@PostMapping("/shows/new")
	public String addNewShow(@Valid @ModelAttribute("show") Show show, BindingResult result, Model model) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		if (result.hasErrors()) {
			return "create_show.jsp";
		} else {
			Show savedShow =showService.addShow(show,result);
			
	        if (savedShow != null) {
	            // Show added successfully, redirect to a page displaying the new show
	        	Long userId = (Long) session.getAttribute("userId");
	        	User user = userService.findById(userId);
	        	user.getShows().add(show);
	        	userService.updateUser(user);
	        	
	            return "redirect:/shows";
	        } else {
	        	model.addAttribute("bindingResult", result);
	            return "create_show.jsp";
	        }
		}
	}
	// show one show
	@GetMapping("/shows/{id}")
	public String viewShow(@PathVariable("id") Long id,@ModelAttribute("review") Review review, Model model) {
		if (session.getAttribute("userId") == null)  {
			return "redirect:/logout";
		}
		Show show = showService.findById(id);
		model.addAttribute("show", show);
	    model.addAttribute("reviews", reviewService.findByRate(show));
		return "view_show.jsp";
	}
	

	@PostMapping("/shows/{id}/review")
	public String leaveReview(@PathVariable("id") Long id, @Valid @ModelAttribute("review") Review review, BindingResult result, Model model) {
	    // Check if the user is authenticated
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		
		  // Check if the user has already reviewed the show
	    Show show = showService.findById(id);
	    if (reviewService.hasUserReviewedShow(user, show)) {
	        // You might want to handle this case, e.g., display an error message
	        model.addAttribute("error", "You have already reviewed this show.");
	        model.addAttribute("show", show); // Include the show in the model for display purposes
	        model.addAttribute("reviews", reviewService.findByRate(show));
	        return  "view_show.jsp";
	    }
	    
		if(result.hasErrors()) {
			model.addAttribute("show", showService.findById(id));
			return "view_show.jsp";
		}else {
	        // Add the review
			reviewService.addReview(new Review(review.getRating(), user, show));
			Float newRating = 0f;
			int count = 0;
			for(Review r:reviewService.findByRate(show)) {
				newRating += r.getRating();
				count++;
			}
			
			show.setRating(newRating/count);
			showService.updateShow(show);
		}
		return "redirect:/shows/"+id;
		}

	// Edit show
	@GetMapping("/shows/{id}/edit")
	public String openEditShow(@PathVariable("id") Long id, Model model) {
	    Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/logout";
		}
		Show show = showService.findById(id);
		  // Check if the user is the owner of the show
	    if (show.getOwner().getId().equals(userId)) {
	    	model.addAttribute("show", show);	    	
	    	return "edit_show.jsp";
	    }else {
	        // If the user is not the owner, redirect or show an error page
	        return "redirect:/shows"; // You can customize this URL
	    }
	}

	@PostMapping("/shows/edit/{id}")
	public String editShow(@PathVariable("id") Long id, @Valid @ModelAttribute("show") Show show, BindingResult result,
			HttpSession session) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");

		User user = userService.findById(userId);

		if (result.hasErrors()) {
			return "edit_show.jsp";
		} else {
			Show thisShow = showService.findById(id);
			show.setUsers(thisShow.getUsers());
			show.setOwner(user);
			showService.updateShow(show);
			return "redirect:/shows";
		}
	}

	// Delete show
	@DeleteMapping("/shows/delete/{id}")
	public String deleteShow(@PathVariable("id") Long id) {

		if (session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}

		Show show = showService.findById(id);
		showService.deleteShow(show);

		return "redirect:/shows";
	}
}
