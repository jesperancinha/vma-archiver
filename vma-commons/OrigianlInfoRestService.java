package com.steelzack.vma.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.steelzack.vma.dao.OriginalInfoDao;
import com.steelzack.vma.entities.OriginalInfo;

@Component
@Path("/originalInfoService")
public class OrigianlInfoRestService {

	@Autowired
	private OriginalInfoDao originalInfoDao;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createOriginalInfo(OriginalInfo originalInfo) {
		originalInfoDao.createOriginalInfo(originalInfo);
		return Response.status(201).entity("A new originalInfo has been created").build();
	}
}
