package com.istad.dataanalyticrestfulapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class response {
    public static class Response<T> {
        public void success(Status createSuccess) {

        }

        public enum Status{
            OK, BAD_REQUEST, UNAUTHORIZED, EXCEPTION,
            VALIDATION_EXCEPTION, WRONG_CREDENTIAL, ACCESS_DENIED,
            NOT_FOUND, DUPLICATE_ENTRY, SUCCESS_DELETE, CREATE_SUCCESS ,
            UPDATE_SUCCESS
        }
        private T payload;
        private Object error;
        private boolean success = false;
        private  Object metadata;
        private Status status;
        }
        public static <T> Response<T> createSuccess(){
            Response<T> response = new Response<>();
            response.success(Response.Status.CREATE_SUCCESS);
            response.success(true);
            return response;
        }



}
