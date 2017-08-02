package com.omniwyse.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.sms.models.House;
import com.omniwyse.sms.services.HouseService;
import com.omniwyse.sms.utils.HouseDTO;
import com.omniwyse.sms.utils.Response;
@RestController
@RequestMapping("/{tenantId}")
public class HouseController {

	@Autowired
	HouseService service;
	@Autowired
	private Response response;

	@RequestMapping(value = "/listhouses", method = RequestMethod.POST, produces = "application/json")
	public List<House> listHouses(@PathVariable("tenantId") long tenantId) {
		return service.listHouses(tenantId);
	}

	@RequestMapping(value = "/addhousedetails", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> addHouseDetails(@PathVariable("tenantId") long tenantId,@RequestBody House house) {
		int rowEffected = service.addHouseDetails(house,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("house details added successfully");
			response.setDescription("house details added successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("housename already exist");
			response.setDescription("housename already exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/edithousedetails", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> editHouseDetails(@PathVariable("tenantId") long tenantId,@RequestBody House house) {
		int rowEffected = service.editHouseDetails(house,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("edited successfully");
			response.setDescription("edited successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("housename already exist");
			response.setDescription("housename already exist");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}
	@RequestMapping(value = "/deletehousedetails", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> deleteHouseDetails(@PathVariable("tenantId") long tenantId,@RequestBody House house) {
		int rowEffected = service.deleteHouseDetails(house,tenantId);
		if (rowEffected > 0) {
			response.setStatus(200);
			response.setMessage("deleteted successfully");
			response.setDescription("deleted successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("students exist in house can't delete house details");
			response.setDescription("students exist in house can't delete house details");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

}
	@RequestMapping(value = "/listnoofstudentsinhouse", method = RequestMethod.POST, produces = "application/json")
	public List<HouseDTO> listnoOfStudentsInHouse(@PathVariable("tenantId") long tenantId,@RequestBody House house) {
		return service.listnoOfStudentsInHouse(house,tenantId);
	}
}
