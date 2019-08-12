package org.danny;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("date/{dateString}")
public class DateResource {

    @GET
    @Produces(value = {MediaType.TEXT_PLAIN, "text/shortdate"})
    public Date getRequestedDate(@PathParam("dateString") MyDate myDate) {
        return Calendar.getInstance().getTime();
    }
}
