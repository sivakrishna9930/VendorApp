package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.ICustomerDao;
import com.app.model.Customer;
import com.app.util.CodecUtil;

@Component
public class CustomerValidator {
	@Autowired
	private CodecUtil codecUtil;
	@Autowired
	private ICustomerDao dao;
	
	private boolean checkCustTypeSeller(String custType){
		return "Seller".equals(custType);
	}
	private boolean checkReqAndDbForPwdTok(String reqPwd,String reqToken,Customer cust){
		
		String encPwd=cust.getPassword();
		String encTok=cust.getAccTock();
		
		String pwd=codecUtil.doDecode(encPwd);
		String tok=codecUtil.doDecode(encTok);
		
		return reqPwd.equals(pwd) && reqToken.equals(tok);
	}
	public boolean checkCustSellerDetailsValid(String reqPwd,String reqTok,Customer cust){
		return checkCustTypeSeller(cust.getCustType()) 
				&& checkReqAndDbForPwdTok(reqPwd, reqTok, cust);
	}
}






