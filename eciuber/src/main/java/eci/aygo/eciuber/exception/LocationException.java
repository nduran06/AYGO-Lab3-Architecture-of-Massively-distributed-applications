package eci.aygo.eciuber.exception;

@SuppressWarnings("serial")
public class LocationException extends RuntimeException {
	
    public LocationException(String errorMsg) {
        super(errorMsg);
    }

    public LocationException(String errorMsg, Throwable err) {
        super(errorMsg, err);
    }
}
