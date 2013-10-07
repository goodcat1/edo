package ru.edo.goodcat.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;

public class BookerService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4416566917201173141L;
	@Inject
	private Logger log;

	@Inject
	TicketService ticketService;

	int money;

	@PostConstruct
	public void createCustomer() {
		this.money = 100;
	}

	public void bookSeat(Long seatId, int price) {
		FacesContext fc = FacesContext.getCurrentInstance();

		if (price > money) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Not enough money", "Regsistration error");

			fc.addMessage(null, m);

			return;
		}

		log.info("Book seat " + seatId);

		ticketService.bookSeat(seatId);

		money = money - price;

		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Registered", "Registration successful");
		fc.addMessage(null, m);
		log.info("Seat booked");
	}

	public int getMoney() {
		return money;
	}
}
