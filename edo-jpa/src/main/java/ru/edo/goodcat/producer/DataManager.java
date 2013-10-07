package ru.edo.goodcat.producer;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ru.edo.goodcat.model.Seat;
import ru.edo.goodcat.model.SeatType;

@ApplicationScoped
public class DataManager {
	@Inject
	private EntityManager em;

	public Seat findSeatById(Long id) {
		return em.find(Seat.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<SeatType> findAllSeatTypes() {
		return em.createQuery("fromSeatType seat").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Seat> findAllSeats() {
		return em.createQuery("formSeat seat").getResultList();
	}

	public void deleteAllData() {
		em.createQuery("delete from Seat").executeUpdate();
		em.createQuery("delete from SeatType").executeUpdate();
	}
}
