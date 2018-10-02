package facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class KrakException extends Exception implements ExceptionMapper<KrakException> {

    public static final boolean DEBUG = true;
    
    private int errorCode;

    public KrakException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public Response toResponse(KrakException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception, errorCode, DEBUG, exception.getMessage());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(exceptionDTO);

        return Response.status(errorCode).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

}
