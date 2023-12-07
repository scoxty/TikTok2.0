/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

    package com.xty.dubbo_gen.user;

import org.apache.dubbo.common.stream.StreamObserver;
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    String JAVA_SERVICE_NAME = "com.xty.dubbo_gen.user.UserService";
    String SERVICE_NAME = "douyin.core.UserService";

        /**
         * <pre>
         *  用户id
         * </pre>
         */
    com.xty.dubbo_gen.user.UserRegisterResponse register(com.xty.dubbo_gen.user.UserRegisterRequest request);

    default CompletableFuture<com.xty.dubbo_gen.user.UserRegisterResponse> registerAsync(com.xty.dubbo_gen.user.UserRegisterRequest request){
        return CompletableFuture.completedFuture(register(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void register(com.xty.dubbo_gen.user.UserRegisterRequest request, StreamObserver<com.xty.dubbo_gen.user.UserRegisterResponse> responseObserver){
        registerAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }

        /**
         * <pre>
         *  用户名称
         * </pre>
         */
    com.xty.dubbo_gen.user.UserLoginResponse login(com.xty.dubbo_gen.user.UserLoginRequest request);

    default CompletableFuture<com.xty.dubbo_gen.user.UserLoginResponse> loginAsync(com.xty.dubbo_gen.user.UserLoginRequest request){
        return CompletableFuture.completedFuture(login(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void login(com.xty.dubbo_gen.user.UserLoginRequest request, StreamObserver<com.xty.dubbo_gen.user.UserLoginResponse> responseObserver){
        loginAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }

        /**
         * <pre>
         *  关注总数
         * </pre>
         */
    com.xty.dubbo_gen.user.UserInfoResponse getUserInfo(com.xty.dubbo_gen.user.UserInfoRequest request);

    default CompletableFuture<com.xty.dubbo_gen.user.UserInfoResponse> getUserInfoAsync(com.xty.dubbo_gen.user.UserInfoRequest request){
        return CompletableFuture.completedFuture(getUserInfo(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getUserInfo(com.xty.dubbo_gen.user.UserInfoRequest request, StreamObserver<com.xty.dubbo_gen.user.UserInfoResponse> responseObserver){
        getUserInfoAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }






}
