package me.vvsos1.demobootaws.config.auth;

import lombok.RequiredArgsConstructor;
import me.vvsos1.demobootaws.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // LoginUser 어노테이션이 존재하는지
        boolean isLoginUserAnntation = parameter.getParameterAnnotation(LoginUser.class) != null;

        // 파라미터의 타입이 SessionUser인지
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnntation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return httpSession.getAttribute("user");
    }
}
