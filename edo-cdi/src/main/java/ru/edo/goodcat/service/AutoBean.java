package ru.edo.goodcat.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ru.edo.goodcat.ejb.Processor;
import ru.edo.goodcat.model.Data;

@Stateless
public class AutoBean {
	@Inject
	Processor processor;
	@Inject
	PollerBean pollerBean;
	@Resource
	private TimerService timerService;

	private final static int MAX = 10;

	private final static Logger log = Logger.getLogger(AutoBean.class);

	public void cancelTimers() {
		for (Timer timer : timerService.getTimers()) {
			timer.cancel();
		}
	}

	@Schedule(dayOfWeek = "*", hour = "*", minute = "*", second = "*/30", year = "*", persistent = false)
	public void backgroundProcessing() {
		Long idValue = findValues();

		if (idValue == null) {
			pollerBean.setPollingActive(false);
			cancelTimers();
			log.info("Scheduler stopped");
			return;
		}

		processor.addValue(new Data() {
			{
				setId(3L);
				setValue("Manual data");
			}
		});

		log.info("Manual data added");
	}

	public Long findValues() {
		List<Data> data = processor.getValues();
		if (data.size() < 1 || data.size() >= MAX)
			return null;
		return data.get(0).getId();
	}
}
