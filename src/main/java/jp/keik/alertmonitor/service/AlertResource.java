package jp.keik.alertmonitor.service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import jp.keik.alertmonitor.domain.Alert;
import jp.keik.alertmonitor.presentation.AlertWebSocket;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/alerts")
public class AlertResource {

	private static Map<Integer, Alert> alertDB = new ConcurrentHashMap<Integer, Alert>();
	private static AtomicInteger idCounter = new AtomicInteger();

	public AlertResource() {
	}

	@GET
	@Produces("application/json")
	public Collection<Alert> getAlerts() {
		Collection<Alert> alerts = alertDB.values();
		if (alerts.size() < 1)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		else
			return alerts;
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Alert getAlert(@PathParam("id") int id) {
		Alert alert = alertDB.get(id);
		if (alert == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		else
			return alert;
	}

	@POST
	@Consumes("application/json")
	public Response createAlert(Alert alert) throws JsonGenerationException,
			JsonMappingException, IOException {
		int id = idCounter.incrementAndGet();
		alert.id = id;
		alertDB.put(alert.id, alert);
		System.out.println("Created customer " + alert.id);

		// Push on WebSocket
		ObjectMapper mapper = new ObjectMapper();
		Writer w = new StringWriter();
		mapper.writeValue(w, alert);
		AlertWebSocket.getInstance().onMessage(w.toString());

		return Response.created(URI.create("/alerts/" + alert.id)).build();
	}
}
