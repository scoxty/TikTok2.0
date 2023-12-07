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

    package com.xty.dubbo_gen.favorite;

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

public final class DubboFavoriteServiceTriple {

    public static final String SERVICE_NAME = FavoriteService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,FavoriteService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,FavoriteServiceProto.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboFavoriteServiceTriple::newStub);
        StubSuppliers.addSupplier(FavoriteService.JAVA_SERVICE_NAME,  DubboFavoriteServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(FavoriteService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static FavoriteService newStub(Invoker<?> invoker) {
        return new FavoriteServiceStub((Invoker<FavoriteService>)invoker);
    }

    /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
    private static final StubMethodDescriptor favoriteActionMethod = new StubMethodDescriptor("FavoriteAction",
    com.xty.dubbo_gen.favorite.FavoriteActionRequest.class, com.xty.dubbo_gen.favorite.FavoriteActionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteActionRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteActionResponse::parseFrom);

    private static final StubMethodDescriptor favoriteActionAsyncMethod = new StubMethodDescriptor("FavoriteAction",
    com.xty.dubbo_gen.favorite.FavoriteActionRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteActionRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteActionResponse::parseFrom);

    private static final StubMethodDescriptor favoriteActionProxyAsyncMethod = new StubMethodDescriptor("FavoriteActionAsync",
    com.xty.dubbo_gen.favorite.FavoriteActionRequest.class, com.xty.dubbo_gen.favorite.FavoriteActionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteActionRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteActionResponse::parseFrom);

    /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
    private static final StubMethodDescriptor getFavoriteListMethod = new StubMethodDescriptor("GetFavoriteList",
    com.xty.dubbo_gen.favorite.FavoriteListRequest.class, com.xty.dubbo_gen.favorite.FavoriteListResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteListRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteListResponse::parseFrom);

    private static final StubMethodDescriptor getFavoriteListAsyncMethod = new StubMethodDescriptor("GetFavoriteList",
    com.xty.dubbo_gen.favorite.FavoriteListRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteListRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteListResponse::parseFrom);

    private static final StubMethodDescriptor getFavoriteListProxyAsyncMethod = new StubMethodDescriptor("GetFavoriteListAsync",
    com.xty.dubbo_gen.favorite.FavoriteListRequest.class, com.xty.dubbo_gen.favorite.FavoriteListResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.FavoriteListRequest::parseFrom,
    com.xty.dubbo_gen.favorite.FavoriteListResponse::parseFrom);

    /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
    private static final StubMethodDescriptor getVideoFavoriteCountMethod = new StubMethodDescriptor("GetVideoFavoriteCount",
    com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getVideoFavoriteCountAsyncMethod = new StubMethodDescriptor("GetVideoFavoriteCount",
    com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getVideoFavoriteCountProxyAsyncMethod = new StubMethodDescriptor("GetVideoFavoriteCountAsync",
    com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
    private static final StubMethodDescriptor getUserFavoriteCountMethod = new StubMethodDescriptor("GetUserFavoriteCount",
    com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getUserFavoriteCountAsyncMethod = new StubMethodDescriptor("GetUserFavoriteCount",
    com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getUserFavoriteCountProxyAsyncMethod = new StubMethodDescriptor("GetUserFavoriteCountAsync",
    com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    /**
         * <pre>
         *  视频的点赞总数
         * </pre>
         */
    private static final StubMethodDescriptor getUserTotalFavoriteCountMethod = new StubMethodDescriptor("GetUserTotalFavoriteCount",
    com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getUserTotalFavoriteCountAsyncMethod = new StubMethodDescriptor("GetUserTotalFavoriteCount",
    com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    private static final StubMethodDescriptor getUserTotalFavoriteCountProxyAsyncMethod = new StubMethodDescriptor("GetUserTotalFavoriteCountAsync",
    com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest.class, com.xty.dubbo_gen.favorite.GetFavoriteCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest::parseFrom,
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse::parseFrom);

    /**
         * <pre>
         *  视频的评论总数
         * </pre>
         */
    private static final StubMethodDescriptor isUserFavoriteMethod = new StubMethodDescriptor("IsUserFavorite",
    com.xty.dubbo_gen.favorite.IsUserFavoriteRequest.class, com.xty.dubbo_gen.favorite.IsUserFavoriteResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.IsUserFavoriteRequest::parseFrom,
    com.xty.dubbo_gen.favorite.IsUserFavoriteResponse::parseFrom);

    private static final StubMethodDescriptor isUserFavoriteAsyncMethod = new StubMethodDescriptor("IsUserFavorite",
    com.xty.dubbo_gen.favorite.IsUserFavoriteRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.IsUserFavoriteRequest::parseFrom,
    com.xty.dubbo_gen.favorite.IsUserFavoriteResponse::parseFrom);

    private static final StubMethodDescriptor isUserFavoriteProxyAsyncMethod = new StubMethodDescriptor("IsUserFavoriteAsync",
    com.xty.dubbo_gen.favorite.IsUserFavoriteRequest.class, com.xty.dubbo_gen.favorite.IsUserFavoriteResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.favorite.IsUserFavoriteRequest::parseFrom,
    com.xty.dubbo_gen.favorite.IsUserFavoriteResponse::parseFrom);





    public static class FavoriteServiceStub implements FavoriteService{
        private final Invoker<FavoriteService> invoker;

        public FavoriteServiceStub(Invoker<FavoriteService> invoker) {
            this.invoker = invoker;
        }

            /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.FavoriteActionResponse favoriteAction(com.xty.dubbo_gen.favorite.FavoriteActionRequest request){
            return StubInvocationUtil.unaryCall(invoker, favoriteActionMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.FavoriteActionResponse> favoriteActionAsync(com.xty.dubbo_gen.favorite.FavoriteActionRequest request){
            return StubInvocationUtil.unaryCall(invoker, favoriteActionAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
        @Override
        public void favoriteAction(com.xty.dubbo_gen.favorite.FavoriteActionRequest request, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteActionResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, favoriteActionMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.FavoriteListResponse getFavoriteList(com.xty.dubbo_gen.favorite.FavoriteListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getFavoriteListMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.FavoriteListResponse> getFavoriteListAsync(com.xty.dubbo_gen.favorite.FavoriteListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getFavoriteListAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
        @Override
        public void getFavoriteList(com.xty.dubbo_gen.favorite.FavoriteListRequest request, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getFavoriteListMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getVideoFavoriteCount(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getVideoFavoriteCountMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getVideoFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getVideoFavoriteCountAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
        @Override
        public void getVideoFavoriteCount(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getVideoFavoriteCountMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserFavoriteCount(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserFavoriteCountMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getUserFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserFavoriteCountAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
        @Override
        public void getUserFavoriteCount(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserFavoriteCountMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频的点赞总数
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserTotalFavoriteCount(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserTotalFavoriteCountMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getUserTotalFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getUserTotalFavoriteCountAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频的点赞总数
         * </pre>
         */
        @Override
        public void getUserTotalFavoriteCount(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getUserTotalFavoriteCountMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频的评论总数
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.favorite.IsUserFavoriteResponse isUserFavorite(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request){
            return StubInvocationUtil.unaryCall(invoker, isUserFavoriteMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse> isUserFavoriteAsync(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request){
            return StubInvocationUtil.unaryCall(invoker, isUserFavoriteAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频的评论总数
         * </pre>
         */
        @Override
        public void isUserFavorite(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request, StreamObserver<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, isUserFavoriteMethod , request, responseObserver);
        }



    }

    public static abstract class FavoriteServiceImplBase implements FavoriteService, ServerService<FavoriteService> {

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
        public final Invoker<FavoriteService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/FavoriteAction" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/FavoriteActionAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetFavoriteList" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetFavoriteListAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetVideoFavoriteCount" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetVideoFavoriteCountAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserFavoriteCount" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserFavoriteCountAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserTotalFavoriteCount" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetUserTotalFavoriteCountAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/IsUserFavorite" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/IsUserFavoriteAsync" );

            BiConsumer<com.xty.dubbo_gen.favorite.FavoriteActionRequest, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteActionResponse>> favoriteActionFunc = this::favoriteAction;
            handlers.put(favoriteActionMethod.getMethodName(), new UnaryStubMethodHandler<>(favoriteActionFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.FavoriteActionRequest, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteActionResponse>> favoriteActionAsyncFunc = syncToAsync(this::favoriteAction);
            handlers.put(favoriteActionProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(favoriteActionAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.FavoriteListRequest, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteListResponse>> getFavoriteListFunc = this::getFavoriteList;
            handlers.put(getFavoriteListMethod.getMethodName(), new UnaryStubMethodHandler<>(getFavoriteListFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.FavoriteListRequest, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteListResponse>> getFavoriteListAsyncFunc = syncToAsync(this::getFavoriteList);
            handlers.put(getFavoriteListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getFavoriteListAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getVideoFavoriteCountFunc = this::getVideoFavoriteCount;
            handlers.put(getVideoFavoriteCountMethod.getMethodName(), new UnaryStubMethodHandler<>(getVideoFavoriteCountFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getVideoFavoriteCountAsyncFunc = syncToAsync(this::getVideoFavoriteCount);
            handlers.put(getVideoFavoriteCountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getVideoFavoriteCountAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getUserFavoriteCountFunc = this::getUserFavoriteCount;
            handlers.put(getUserFavoriteCountMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserFavoriteCountFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getUserFavoriteCountAsyncFunc = syncToAsync(this::getUserFavoriteCount);
            handlers.put(getUserFavoriteCountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserFavoriteCountAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getUserTotalFavoriteCountFunc = this::getUserTotalFavoriteCount;
            handlers.put(getUserTotalFavoriteCountMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserTotalFavoriteCountFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse>> getUserTotalFavoriteCountAsyncFunc = syncToAsync(this::getUserTotalFavoriteCount);
            handlers.put(getUserTotalFavoriteCountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getUserTotalFavoriteCountAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.IsUserFavoriteRequest, StreamObserver<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse>> isUserFavoriteFunc = this::isUserFavorite;
            handlers.put(isUserFavoriteMethod.getMethodName(), new UnaryStubMethodHandler<>(isUserFavoriteFunc));
            BiConsumer<com.xty.dubbo_gen.favorite.IsUserFavoriteRequest, StreamObserver<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse>> isUserFavoriteAsyncFunc = syncToAsync(this::isUserFavorite);
            handlers.put(isUserFavoriteProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(isUserFavoriteAsyncFunc));




            return new StubInvoker<>(this, url, FavoriteService.class, handlers);
        }


        @Override
        public com.xty.dubbo_gen.favorite.FavoriteActionResponse favoriteAction(com.xty.dubbo_gen.favorite.FavoriteActionRequest request){
            throw unimplementedMethodException(favoriteActionMethod);
        }

        @Override
        public com.xty.dubbo_gen.favorite.FavoriteListResponse getFavoriteList(com.xty.dubbo_gen.favorite.FavoriteListRequest request){
            throw unimplementedMethodException(getFavoriteListMethod);
        }

        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getVideoFavoriteCount(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request){
            throw unimplementedMethodException(getVideoFavoriteCountMethod);
        }

        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserFavoriteCount(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request){
            throw unimplementedMethodException(getUserFavoriteCountMethod);
        }

        @Override
        public com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserTotalFavoriteCount(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request){
            throw unimplementedMethodException(getUserTotalFavoriteCountMethod);
        }

        @Override
        public com.xty.dubbo_gen.favorite.IsUserFavoriteResponse isUserFavorite(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request){
            throw unimplementedMethodException(isUserFavoriteMethod);
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
