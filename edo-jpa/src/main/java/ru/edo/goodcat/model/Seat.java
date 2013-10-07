package ru.edo.goodcat.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seat database table.
 * 
 */
@Entity
@Table(name="seat")
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Object booked;

	//bi-directional many-to-one association to SeatType
	@ManyToOne
	@JoinColumn(name="seat_id")
	private SeatType seatType;

	public Seat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getBooked() {
		return this.booked;
	}

	public void setBooked(Object booked) {
		this.booked = booked;
	}

	public SeatType getSeatType() {
		return this.seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

}