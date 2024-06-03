package com.intercorpretail.AsyncGenericRestLibrary.utill;

import com.intercorpretail.AsyncGenericRestLibrary.annotations.RestRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Util {

    public static <T> Class<T> determineResponseType(Method method) {
        return Stream.of(method.getGenericReturnType())
                .filter(ParameterizedType.class::isInstance)
                .map(ParameterizedType.class::cast)
                .map(ParameterizedType::getActualTypeArguments)
                .filter(args -> args.length > 0)
                .flatMap(Stream::of)
                .flatMap(arg -> {
                    if (arg instanceof Class) {
                        return Stream.of((Class<T>) arg);
                    } else if (arg instanceof ParameterizedType) {
                        Type[] nestedActualTypeArguments = ((ParameterizedType) arg).getActualTypeArguments();
                        if (nestedActualTypeArguments.length > 0 && nestedActualTypeArguments[0] instanceof Class) {
                            return Stream.of((Class<T>) nestedActualTypeArguments[0]);
                        }
                    }
                    return Stream.empty();
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot determine the response type for the method: " + method.getName()));
    }

    public static String getPathFromAnnotation(Method method) {
        RestRequest restRequest = method.getAnnotation(RestRequest.class);
        return restRequest != null ? restRequest.path() : "";
    }

    public static Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }

}
