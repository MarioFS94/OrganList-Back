package es.organlist.config;


import org.springframework.boot.web.error.ErrorAttributeOptions;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        errorAttributes.remove("status");
        errorAttributes.remove("error");

        //cogemos la key del parametro
        String param = webRequest.getParameterNames().next();
        //cogemos el value del parametro
        String value = Arrays.stream(webRequest.getParameterValues(param)).findAny().get();

        errorAttributes.replace("path", errorAttributes.get("path") + "?" + param + "=" + value);

        return errorAttributes;
    }

}
