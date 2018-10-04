package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class KrakException extends Exception implements ExceptionMapper<KrakException> {

    public static final boolean DEBUG = true;

    private int errorCode;

    public KrakException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public KrakException() {
        super();
    }

    public int getErrorCode() {
        return errorCode;
    }
    

    @Override
    public Response toResponse(KrakException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception, exception.getErrorCode(), DEBUG, exception.getMessage());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(exceptionDTO);

        return Response.status(exception.getErrorCode()).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

}
