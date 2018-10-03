package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static exception.KrakException.DEBUG;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception, 400, DEBUG, exception.getMessage());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(exceptionDTO);

        return Response.status(400).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

}
