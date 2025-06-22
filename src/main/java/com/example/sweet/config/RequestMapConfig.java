package com.example.sweet.config;

import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.util.annotation.ApiMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// Cái này nằm ở một object khác vì nó phải có Qualifier và @AllArgsConstructor không cho qualifier
@Configuration
public class RequestMapConfig {

    private final RequestMappingHandlerMapping handlerMapping;

    public RequestMapConfig(@Qualifier("requestMappingHandlerMapping")
                                RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public List<QuyenHan> getRequestMapToQuyenHan() {
        ArrayList<QuyenHan> quyenHans = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();


            ApiMessage desc = method.getMethodAnnotation(ApiMessage.class);
            String description = (desc != null) ? desc.value() : method.getMethod().getName();
            String methodName = info.getMethodsCondition().toString().replace("[", "").replace("]", "");
            String moduleName = method.getBeanType().getSimpleName().replace("Controller", "");
            if (info.getPatternValues().isEmpty() || methodName.isEmpty()) {
                System.err.println("No patterns defined for method: " + method.getMethod().getName());
                continue; // Skip if no patterns are defined
            }


            quyenHans.add(new QuyenHan(
                null,
                description,
                info.getPatternValues().toArray()[0].toString(),
                methodName,
                moduleName,
                List.of()
            ));
        }
        quyenHans.add(new QuyenHan(
            null,
            "Error",
            "/error",
            "GET",
            "Main",
            List.of()
        ));
        return quyenHans;
    }
}