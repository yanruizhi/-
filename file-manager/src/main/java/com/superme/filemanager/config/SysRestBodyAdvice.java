package com.superme.filemanager.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superme.common.beans.Result;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现 ResponseBodyAdvice接口，并重写其中的方法，即可对我们的controller进行增强操作
 *
 * 不管Controller中 是否使用ResultVO<T>对返回结果包装，
 * 均可被 ResponseBodyAdvice接口 处理返回数据，以使系统 统一结果，统一异常，统一日志
 */
// 注意哦，这里要加上需要扫描的包
@RestControllerAdvice(basePackages = {"com.superme.filemanager.controller"})
public class SysRestBodyAdvice implements ResponseBodyAdvice<Object> {
    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * supports方法要返回为true才会执行beforeBodyWrite方法，
     * 所以如果有些情况不需要进行增强操作可以在supports方法里进行判断
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(Result.class);
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * 对返回数据进行真正的操作还是在beforeBodyWrite方法中，
     * 我们可以直接在该方法里包装数据，这样就不需要每个接口都进行数据包装了，省去了很多麻烦
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在Result里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(Result.OK(body));
            } catch (JsonProcessingException e) {
                throw new Exception("返回String类型错误");
            }
        }
        // 将原本的数据包装在Result里
        return Result.OK(body);
    }
}
