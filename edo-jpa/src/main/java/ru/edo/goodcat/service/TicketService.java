package ru.edo.goodcat.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import ru.edo.goodcat.model.Seat;
import ru.edo.goodcat.model.SeatType;
import ru.edo.goodcat.producer.DataManager;

@Stateless
public class TicketService {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	Event<SeatType> seatTypeEventSrc;

	@Inject
	private Event<Seat> seatEventSrc;

	@Inject
	List<SeatType> seatTypes;

	@Inject
	private DataManager repository;

	public void createSeatType(SeatType seatType) throws Exception {
		log.info("Registering " + seatType.getDescription());
		em.persist(seatType);
		seatTypeEventSrc.fire(seatType);
	}

	public void createTheatre(List<SeatType> seatTypes) {
		for (SeatType type : seatTypes) {
			for (int i = 0; i < type.getQuantity(); i++) {
				Seat seat = new Seat();
				seat.setBooked(false);
				seat.setSeatType(type);
				em.persist(seat);
			}
		}
	}

	public void bookSeat(Long seatId) {
		Seat seat = repository.findSeatById(seatId);
		seat.setBooked(true);
		em.persist(seat);
		seatEventSrc.fire(seat);
	}

	public void doCleanUp() {
		repository.deleteAllData();
	}
}
