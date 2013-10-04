package ru.goodcat.edo.ejb;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.jboss.logging.Logger;

@Stateful
@Remote(ProcessorWorker.class)
public class ProcessorWorkerBean implements ProcessorWorker {
	private static final Logger log = Logger
			.getLogger(ProcessorWorkerBean.class);

	@EJB
	Processor processor;

	@Override
	public void addData(String value) throws AddDataException {
		log.infov("Adding data, value={}", String.valueOf(value));

		if (value == null)
			throw new AddDataException("Data is null");

		processor.setVal(value);
	}

}
