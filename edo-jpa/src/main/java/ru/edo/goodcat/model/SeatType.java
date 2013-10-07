package ru.edo.goodcat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The persistent class for the seat_type database table.
 * 
 */
@Entity
@Table(name = "seat_type")
@NamedQuery(name = "SeatType.findAll", query = "SELECT s FROM SeatType s")
public class SeatType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 1, max = 25, message = "Seat description max 25 chars")
	@Pattern(regexp = "[A-Za-z ]*", message = "Description must contain only letters and spaces")
	private String description;

	@NotNull
	private int price;

	@NotNull
	private int quantity;

	// bi-directional many-to-one association to Seat
	@OneToMany(mappedBy = "seatType")
	private Set<Seat> seats;

	public SeatType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Seat addSeat(Seat seat) {
		getSeats().add(seat);
		seat.setSeatType(this);

		return seat;
	}

	public Seat removeSeat(Seat seat) {
		getSeats().remove(seat);
		seat.setSeatType(null);

		return seat;
	}

}