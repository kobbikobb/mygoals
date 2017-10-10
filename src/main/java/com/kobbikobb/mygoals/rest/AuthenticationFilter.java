package com.kobbikobb.mygoals.rest;

// https://howtodoinjava.com/jersey/jersey-rest-security/

import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Access blocked for all users !!").build();

    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        if(method.isAnnotationPresent(DenyAll.class))
        {
            requestContext.abortWith(ACCESS_FORBIDDEN);
            return;
        }

        UserAuthentication userAuthentication = findUserAuthentication(requestContext);
        if(userAuthentication == null)
        {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }

        if(!isUserAllowed(userAuthentication))
        {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }
    }

    public UserAuthentication findUserAuthentication(ContainerRequestContext requestContext) {
        List<String> authorization = requestContext.getHeaders().get(AUTHORIZATION_PROPERTY);
        if(authorization == null || authorization.isEmpty())
        {
            return null;
        }

        String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

        StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        return new UserAuthentication(username, password);
    }

    private boolean isUserAllowed(UserAuthentication userAuthentication)
    {
        return userAuthentication.getUsername().equals("admin") && userAuthentication.getPassword().equals("1234");
    }

    private static class UserAuthentication {
        private String username;
        private String password;

        public UserAuthentication(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}