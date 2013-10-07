package ru.edo.goodcat;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ru.edo.goodcat.ejb.Processor;
import ru.edo.goodcat.model.Data;

@Named
@SessionScoped
public class ProcessorWorkerBean implements Serializable {
	private static final long serialVersionUID = 339787796833486362L;

	private static final Logger log = Logger
			.getLogger(ProcessorWorkerBean.class);

	@Inject
	Processor processor;

	public void addValue(Long id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		log.infov("Adding value {}", id);

		Data value = new Data();

		value.setId(id);
		value.setValue("Added");

		processor.addValue(value);

		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Added!", "Value added");
		facesContext.addMessage(null, facesMessage);
	}
}
