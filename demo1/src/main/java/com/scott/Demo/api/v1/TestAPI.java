package com.scott.Demo.api.v1;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.scott.Demo.model.MyBean;

@Path("/test")
@Singleton
public class TestAPI
{
	@GET
	@Path("/xml")
	@Produces({ "text/xml", "application/xml" })
	public MyBean getXml()
	{
		return new MyBean();
	}

	@GET
	@Path("/json")
	@Produces("application/json")
	public MyBean getJson()
	{
		return new MyBean();
	}
}
