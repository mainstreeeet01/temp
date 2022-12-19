package com.example.mobileprogramming.network.data.LCRiseSetInfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="header", strict = false)
public class Header {
    @Element(name="resultCode")
    String resultCode;
    @Element(name="resultMsg")
    String resultMsg ;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
