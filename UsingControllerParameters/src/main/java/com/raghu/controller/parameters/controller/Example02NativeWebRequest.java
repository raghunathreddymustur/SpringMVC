package com.raghu.controller.parameters.controller;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;

import static com.raghu.controller.parameters.utils.JoinUtils.join;
import static java.lang.String.format;

@Controller
public class Example02NativeWebRequest {
    // curl "localhost:8080/actionB?name=John&country=US"
    @GetMapping("/actionB")
    @ResponseBody
    public void actionB(NativeWebRequest nativeWebRequest) throws IOException {
        RequestFacade requestFacade = nativeWebRequest.getNativeRequest(RequestFacade.class);
        ResponseFacade responseFacade = nativeWebRequest.getNativeResponse(ResponseFacade.class);

        responseFacade.getOutputStream().println(
                format(
                        "Retrieved request with headers = [%s], parameters = [%s]",
                        join(requestFacade.getHeaderNames()),
                        join(requestFacade.getParameterNames())
                )
        );
    }
}
