package com.steelzack.vma.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.steelzack.vma.model.OriginalInfo;
import com.steelzack.vma.service.OriginalInfoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * RESTful endpoint for Original Info management
 */
@Api(value = "originalinfos", description = "Original info management")
@Path("/originalinfos")
public class OriginalInfosEndpoint {

	@Inject
	private OriginalInfoService originalinfoService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/{originalinfoName}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns originalinfo", notes = "Returns an original info list", response = OriginalInfo.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Successful retrieval of originalinfo", response = OriginalInfo.class), //
			@ApiResponse(code = 404, message = "Could not find OriginalInfo with originalinfo name"), //
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getOriginalInfo(
			@ApiParam(name = "originalinfoName", value = "dummy-original-info", required = true) @PathParam("originalinfoName") String originalinfoName) {
		final OriginalInfo originalinfo = originalinfoService.findByOriginalInfoName(originalinfoName);

		if (originalinfo != null) {
			return Response.status(Status.OK).entity(originalinfo).build();
		} else {
			return Response.status(Status.NOT_FOUND)
					.entity("OriginalInfo with specified originalinfoname does not exist.").build();
		}
	}
}