package com.retobbva.challenge.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retobbva.challenge.jpa.CurrencyRepository;
import com.retobbva.challenge.jpa.RequestRepository;
import com.retobbva.challenge.model.CurrencyModel;
import com.retobbva.challenge.model.RequestModel;


@RestController
@RequestMapping("/api")
public class CurrencyController {

	public static String auditName="ADMIN";
	@Autowired
	private CurrencyRepository currencyJPA;
	
	@Autowired
	private RequestRepository requestJPA;
	

	@GetMapping("/currency")
	public List<CurrencyModel> allPersons(){
		return currencyJPA.findAll();
	}
	
	@GetMapping("/currency/{id}")
	public Optional<CurrencyModel> findById(@PathVariable("id") String id) {
		return currencyJPA.findById(id);
	}
	
	@PostMapping("/currency")
	public CurrencyModel createCurrency(@RequestBody CurrencyModel currency) {
		currency=auditoria(currency, true);
		return currencyJPA.save(currency);
	}
	
	@PatchMapping("/currency/{id}")
	public CurrencyModel updateCurrency(@PathVariable String id ,@RequestBody CurrencyModel currency) {
		currency.setId(id);
		currency=merge(currency);
		currency=auditoria(currency, false);
		return currencyJPA.save(currency);
	}
	
	@DeleteMapping("/currency/{id}")
	public void deleteCurrency(@PathVariable("id") String id) {
		currencyJPA.deleteById(id);
	}
	
	@PostMapping("/currency/request")
	public RequestModel createRequest(@RequestBody RequestModel requestModel) {
		requestModel.setCreatedAt(new Date());
		requestModel.setCreatedBy(auditName);
		requestModel=calculateCurrency(requestModel);
		return requestJPA.save(requestModel);
	}
	
	private RequestModel calculateCurrency(RequestModel requestModel) {
		
		Optional<CurrencyModel> originCurr=findById(requestModel.getOriginCurrency());
		Optional<CurrencyModel> destinationCurr=findById(requestModel.getDestinationCurrency());
			
		double amount=requestModel.getOriginAmount();
		double currentCurrencyAmount=originCurr.get().getValue();
		double newCurrencyAmount=destinationCurr.get().getValue();
		double currencyAmount=currentCurrencyAmount/newCurrencyAmount;
		double destinationAmount=amount*currencyAmount;
		
		requestModel.setCurrencyAmount(currencyAmount);
		requestModel.setDestinationAmount(destinationAmount);
		
		return requestModel;
	}
	
	
	public CurrencyModel auditoria(CurrencyModel currencyModel, boolean isInsert) {
		if(isInsert) {
			currencyModel.setCreatedAt(new Date());
			currencyModel.setCreatedBy(auditName);
		}
		currencyModel.setUpdatedAt(new Date());
		currencyModel.setUpdatedBy(auditName);
		
		return currencyModel;
	}
	
	public CurrencyModel merge(CurrencyModel currencyModel) {
		CurrencyModel currencyModelAux=new CurrencyModel();
		if(currencyModel!=null&&currencyModel.getId()!=null) {
			currencyModelAux=findById(currencyModel.getId()).orElse(new CurrencyModel());
			if (currencyModel.getName()!=null) currencyModelAux.setName(currencyModel.getName());
			if (currencyModel.getValue()!=0) currencyModelAux.setValue(currencyModel.getValue());
		}
		
		return currencyModelAux;
	}
	
}
