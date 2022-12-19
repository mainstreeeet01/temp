package com.example.mobileprogramming.network.data.LCRiseSetInfo;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="response", strict = false)
public class LCRiseSetInfoResponse {

    @Element(name="header")
    Header header;
    @Element(name="body")
    Body body ;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
