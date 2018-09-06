package com.ssm.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.ResponseWrapper;

@WebService
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface HelloService {
	@WebResult(name = "result", targetNamespace = "")  
    @WebMethod(operationName="testHello")  
    @ResponseWrapper(localName = "synCompanyResponse" )  
	public  String testHello (@WebParam(name="msgId") String msgId);  
}
