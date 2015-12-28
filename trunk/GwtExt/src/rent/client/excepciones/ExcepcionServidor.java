package rent.client.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ExcepcionServidor extends RuntimeException implements
		IsSerializable {

	private static final long serialVersionUID = 765394075842392684L;

	private String message;

	public ExcepcionServidor() {
		super();
	}

	public ExcepcionServidor(String message) {
		super(message);
		this.message = message;
	}

	public ExcepcionServidor(Throwable t) {
		super(t.getCause());
		this.message = t.getCause() == null ? t.getMessage() : t.getCause()
				.getMessage();
	}

	public ExcepcionServidor(Throwable t, String message) {
		super(message, t.getCause());
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
