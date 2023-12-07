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
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.PathResolver;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.ServerService;
import org.apache.dubbo.rpc.TriRpcStatus;
import org.apache.dubbo.rpc.model.MethodDescriptor;
import org.apache.dubbo.rpc.model.ServiceDescriptor;
import org.apache.dubbo.rpc.model.StubMethodDescriptor;
import org.apache.dubbo.rpc.model.StubServiceDescriptor;
import org.apache.dubbo.rpc.stub.BiStreamMethodHandler;
import org.apache.dubbo.rpc.stub.ServerStreamMethodHandler;
import org.apache.dubbo.rpc.stub.StubInvocationUtil;
import org.apache.dubbo.rpc.stub.StubInvoker;
import org.apache.dubbo.rpc.stub.StubMethodHandler;
import org.apache.dubbo.rpc.stub.StubSuppliers;
import org.apache.dubbo.rpc.stub.UnaryStubMethodHandler;

import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public final class DubboUserServiceTriple {

    public static final String SERVICE_NAME = UserService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,UserService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,UserServiceProto.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboUserServiceTriple::newStub);
        StubSuppliers.addSupplier(UserService.JAVA_SERVICE_NAME,  DubboUserServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(UserService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static UserService newStub(Invoker<?> invoker) {
        return new UserServiceStub((Invoker<UserService>)invoker);
    }

    /**
         * <pre>
         *  用户id
         * </pre>
         */
    private static final StubMethodDescriptor registerMethod = new StubMethodDescriptor("Register",
    com.xty.dubbo_gen.user.UserRegisterRequest.class, com.xty.dubbo_gen.user.UserRegisterResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserRegisterRequest::parseFrom,
    com.xty.dubbo_gen.user.UserRegisterResponse::parseFrom);

    private static final StubMethodDescriptor registerAsyncMethod = new StubMethodDescriptor("Register",
    com.xty.dubbo_gen.user.UserRegisterRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserRegisterRequest::parseFrom,
    com.xty.dubbo_gen.user.UserRegisterResponse::parseFrom);

    private static final StubMethodDescriptor registerProxyAsyncMethod = new StubMethodDescriptor("RegisterAsync",
    com.xty.dubbo_gen.user.UserRegisterRequest.class, com.xty.dubbo_gen.user.UserRegisterResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserRegisterRequest::parseFrom,
    com.xty.dubbo_gen.user.UserRegisterResponse::parseFrom);

    /**
         * <pre>
         *  用户名称
         * </pre>
         */
    private static final StubMethodDescriptor loginMethod = new StubMethodDescriptor("Login",
    com.xty.dubbo_gen.user.UserLoginRequest.class, com.xty.dubbo_gen.user.UserLoginResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserLoginRequest::parseFrom,
    com.xty.dubbo_gen.user.UserLoginResponse::parseFrom);

    private static final StubMethodDescriptor loginAsyncMethod = new StubMethodDescriptor("Login",
    com.xty.dubbo_gen.user.UserLoginRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserLoginRequest::parseFrom,
    com.xty.dubbo_gen.user.UserLoginResponse::parseFrom);

    private static final StubMethodDescriptor loginProxyAsyncMethod = new StubMethodDescriptor("LoginAsync",
    com.xty.dubbo_gen.user.UserLoginRequest.class, com.xty.dubbo_gen.user.UserLoginResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserLoginRequest::parseFrom,
    com.xty.dubbo_gen.user.UserLoginResponse::parseFrom);

    /**
         * <pre>
         *  关注总数
         * </pre>
         */
    private static final StubMethodDescriptor getUserInfoMethod = new StubMethodDescriptor("GetUserInfo",
    com.xty.dubbo_gen.user.UserInfoRequest.class, com.xty.dubbo_gen.user.UserInfoResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserInfoRequest::parseFrom,
    com.xty.dubbo_gen.user.UserInfoResponse::parseFrom);

    private static final StubMethodDescriptor getUserInfoAsyncMethod = new StubMethodDescriptor("GetUserInfo",
    com.xty.dubbo_gen.user.UserInfoRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserInfoRequest::parseFrom,
    com.xty.dubbo_gen.user.UserInfoResponse::parseFrom);

    private static final StubMethodDescriptor getUserInfoProxyAsyncMethod = new StubMethodDescriptor("GetUserInfoAsync",
    com.xty.dubbo_gen.user.UserInfoRequest.class, com.xty.dubbo_gen.user.UserInfoResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.user.UserInfoRequest::parseFrom,
    com.xty.dubbo_gen.user.UserInfoResponse::parseFrom);





    public static class UserServiceStub implements UserService{
        private final Invoker<UserService> invoker;

        public UserServiceStub(Invoker<UserService> invoker) {
            this.invoker = invoker;
        }

            /**
         * <pre>
         *  用户id
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.user.UserRegisterResponse register(com.xty.dubbo_gen.user.UserRegisterRequest request){
            return StubInvocationUtil.unaryCall(invoker, registerMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.user.UserRegisterResponse> registerAsync(com.xty.dubbo_gen.user.UserRegisterRequest request){
            return StubInvocationUtil.unaryCall(invoker, registerAsyncMethod, request);
        }

            /**
         * <pre>
         *  用户id
         * </pre>
         */
        @Override
        public void register(com.xty.dubbo_gen.user.UserRegisterRequest request, StreamObserver<com.xty.dubbo_gen.user.UserRegisterResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, registerMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  用户名称
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.user.UserLoginResponse login(com.xty.dubbo_gen.user.UserLoginRequest request){
            return StubInvocationUtil.unaryCall(invoker, loginMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.user.UserLoginResponse> loginAsync(com.xty.dubbo_gen.user.UserLoginRequest request){
            return StubInvocationUtil.unaryCall(invoker, loginAsyncMethod, request);
        }

            /**
         * <pre>
         *  用户名称
         * </pre>
         */
        @Override
        public void login(com.xty.dubbo_gen.user.UserLoginRequest request, StreamObserver<com.xty.dubbo_gen.user.UserLoginResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, loginMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  关注总数
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.user.UserInfoResponse getUserInfo(com.xty.dubbo_gen.user.UserInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserInfoMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.user.UserInfoResponse> getUserInfoAsync(com.xty.dubbo_gen.user.UserInfoRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserInfoAsyncMethod, request);
        }

            /**
         * <pre>
         *  关注总数
         * </pre>
         */
        @Override
        public void getUserInfo(com.xty.dubbo_gen.user.UserInfoRequest request, StreamObserver<com.xty.dubbo_gen.user.UserInfoResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserInfoMethod , request, responseObserver);
        }



    }

    public static abstract class UserServiceImplBase implements UserService, ServerService<UserService> {

        private <T, R> BiConsumer<T, StreamObserver<R>> syncToAsync(java.util.function.Function<T, R> syncFun) {
            return new BiConsumer<T, StreamObserver<R>>() {
                @Override
                public void accept(T t, StreamObserver<R> observer) {
                    try {
                        R ret = syncFun.apply(t);
                        observer.onNext(ret);
                        observer.onCompleted();
                    } catch (Throwable e) {
                        observer.onError(e);
                    }
                }
            };
        }

        @Override
        public final Invoker<UserService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/Register" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/RegisterAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/Login" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/LoginAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserInfo" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserInfoAsync" );

            BiConsumer<com.xty.dubbo_gen.user.UserRegisterRequest, StreamObserver<com.xty.dubbo_gen.user.UserRegisterResponse>> registerFunc = this::register;
            handlers.put(registerMethod.getMethodName(), new UnaryStubMethodHandler<>(registerFunc));
            BiConsumer<com.xty.dubbo_gen.user.UserRegisterRequest, StreamObserver<com.xty.dubbo_gen.user.UserRegisterResponse>> registerAsyncFunc = syncToAsync(this::register);
            handlers.put(registerProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(registerAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.user.UserLoginRequest, StreamObserver<com.xty.dubbo_gen.user.UserLoginResponse>> loginFunc = this::login;
            handlers.put(loginMethod.getMethodName(), new UnaryStubMethodHandler<>(loginFunc));
            BiConsumer<com.xty.dubbo_gen.user.UserLoginRequest, StreamObserver<com.xty.dubbo_gen.user.UserLoginResponse>> loginAsyncFunc = syncToAsync(this::login);
            handlers.put(loginProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(loginAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.user.UserInfoRequest, StreamObserver<com.xty.dubbo_gen.user.UserInfoResponse>> getUserInfoFunc = this::getUserInfo;
            handlers.put(getUserInfoMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserInfoFunc));
            BiConsumer<com.xty.dubbo_gen.user.UserInfoRequest, StreamObserver<com.xty.dubbo_gen.user.UserInfoResponse>> getUserInfoAsyncFunc = syncToAsync(this::getUserInfo);
            handlers.put(getUserInfoProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserInfoAsyncFunc));




            return new StubInvoker<>(this, url, UserService.class, handlers);
        }


        @Override
        public com.xty.dubbo_gen.user.UserRegisterResponse register(com.xty.dubbo_gen.user.UserRegisterRequest request){
            throw unimplementedMethodException(registerMethod);
        }

        @Override
        public com.xty.dubbo_gen.user.UserLoginResponse login(com.xty.dubbo_gen.user.UserLoginRequest request){
            throw unimplementedMethodException(loginMethod);
        }

        @Override
        public com.xty.dubbo_gen.user.UserInfoResponse getUserInfo(com.xty.dubbo_gen.user.UserInfoRequest request){
            throw unimplementedMethodException(getUserInfoMethod);
        }





        @Override
        public final ServiceDescriptor getServiceDescriptor() {
            return serviceDescriptor;
        }
        private RpcException unimplementedMethodException(StubMethodDescriptor methodDescriptor) {
            return TriRpcStatus.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented",
                "/" + serviceDescriptor.getInterfaceName() + "/" + methodDescriptor.getMethodName())).asException();
        }
    }

}
