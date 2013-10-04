package ru.goodcat.edo.ejb;

public interface ProcessorWorker {
	void addData(String value) throws AddDataException;
}
