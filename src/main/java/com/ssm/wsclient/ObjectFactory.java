
package com.ssm.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ssm.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestHello_QNAME = new QName("http://webservice.ssm.com/", "testHello");
    private final static QName _SynCompanyResponse_QNAME = new QName("http://webservice.ssm.com/", "synCompanyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ssm.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestHello }
     * 
     */
    public TestHello createTestHello() {
        return new TestHello();
    }

    /**
     * Create an instance of {@link SynCompanyResponse }
     * 
     */
    public SynCompanyResponse createSynCompanyResponse() {
        return new SynCompanyResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssm.com/", name = "testHello")
    public JAXBElement<TestHello> createTestHello(TestHello value) {
        return new JAXBElement<TestHello>(_TestHello_QNAME, TestHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynCompanyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ssm.com/", name = "synCompanyResponse")
    public JAXBElement<SynCompanyResponse> createSynCompanyResponse(SynCompanyResponse value) {
        return new JAXBElement<SynCompanyResponse>(_SynCompanyResponse_QNAME, SynCompanyResponse.class, null, value);
    }

}
