package org.jesperancinha.vma.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.jesperancinha.vma.dao.OriginalInfoDao;
import org.jesperancinha.vma.entities.OriginalInfo;

@Component
@Path("/originalInfoService")
public class OriginalInfoRestService {

	@Autowired
	private OriginalInfoDao originalInfoDao;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	@Transactional
	public Response createOriginalInfo(OriginalInfo originalInfo) {
		originalInfoDao.createOriginalInfo(originalInfo);
		return Response.status(201).entity("A new originalInfo has been created").build();
	}

	/************************************ READ ************************************/
	/**
	 * Returns all resources (originalinfos) from the database
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional
	public List<OriginalInfo> getOriginalInfos() {
		return this.originalInfoDao.getOriginalInfos();
	}

	@Produces
	public void setOriginalInfoDao(OriginalInfoDao originalInfoDao) {
		this.originalInfoDao = originalInfoDao;
	}
}
