package ru.edo.goodcat.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PollerBean implements Serializable {
	private static final long serialVersionUID = -8852452135644358262L;

	boolean pollingActive = true;

	public boolean isPollingActive() {
		return pollingActive;
	}

	public void setPollingActive(boolean pollingActive) {
		this.pollingActive = pollingActive;
	}

}
