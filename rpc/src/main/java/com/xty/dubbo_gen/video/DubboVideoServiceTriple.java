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

    package com.xty.dubbo_gen.video;

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

public final class DubboVideoServiceTriple {

    public static final String SERVICE_NAME = VideoService.SERVICE_NAME;

    private static final StubServiceDescriptor serviceDescriptor = new StubServiceDescriptor(SERVICE_NAME,VideoService.class);

    static {
        org.apache.dubbo.rpc.protocol.tri.service.SchemaDescriptorRegistry.addSchemaDescriptor(SERVICE_NAME,VideoServiceProto.getDescriptor());
        StubSuppliers.addSupplier(SERVICE_NAME, DubboVideoServiceTriple::newStub);
        StubSuppliers.addSupplier(VideoService.JAVA_SERVICE_NAME,  DubboVideoServiceTriple::newStub);
        StubSuppliers.addDescriptor(SERVICE_NAME, serviceDescriptor);
        StubSuppliers.addDescriptor(VideoService.JAVA_SERVICE_NAME, serviceDescriptor);
    }

    @SuppressWarnings("all")
    public static VideoService newStub(Invoker<?> invoker) {
        return new VideoServiceStub((Invoker<VideoService>)invoker);
    }

    /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
    private static final StubMethodDescriptor getVideoFeedMethod = new StubMethodDescriptor("GetVideoFeed",
    com.xty.dubbo_gen.video.FeedRequest.class, com.xty.dubbo_gen.video.FeedResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.FeedRequest::parseFrom,
    com.xty.dubbo_gen.video.FeedResponse::parseFrom);

    private static final StubMethodDescriptor getVideoFeedAsyncMethod = new StubMethodDescriptor("GetVideoFeed",
    com.xty.dubbo_gen.video.FeedRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.FeedRequest::parseFrom,
    com.xty.dubbo_gen.video.FeedResponse::parseFrom);

    private static final StubMethodDescriptor getVideoFeedProxyAsyncMethod = new StubMethodDescriptor("GetVideoFeedAsync",
    com.xty.dubbo_gen.video.FeedRequest.class, com.xty.dubbo_gen.video.FeedResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.FeedRequest::parseFrom,
    com.xty.dubbo_gen.video.FeedResponse::parseFrom);

    /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
    private static final StubMethodDescriptor publishActionMethod = new StubMethodDescriptor("PublishAction",
    com.xty.dubbo_gen.video.PublishActionRequest.class, com.xty.dubbo_gen.video.PublishActionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishActionRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishActionResponse::parseFrom);

    private static final StubMethodDescriptor publishActionAsyncMethod = new StubMethodDescriptor("PublishAction",
    com.xty.dubbo_gen.video.PublishActionRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishActionRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishActionResponse::parseFrom);

    private static final StubMethodDescriptor publishActionProxyAsyncMethod = new StubMethodDescriptor("PublishActionAsync",
    com.xty.dubbo_gen.video.PublishActionRequest.class, com.xty.dubbo_gen.video.PublishActionResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishActionRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishActionResponse::parseFrom);

    /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
    private static final StubMethodDescriptor getPublishListMethod = new StubMethodDescriptor("GetPublishList",
    com.xty.dubbo_gen.video.PublishListRequest.class, com.xty.dubbo_gen.video.PublishListResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishListRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishListResponse::parseFrom);

    private static final StubMethodDescriptor getPublishListAsyncMethod = new StubMethodDescriptor("GetPublishList",
    com.xty.dubbo_gen.video.PublishListRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishListRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishListResponse::parseFrom);

    private static final StubMethodDescriptor getPublishListProxyAsyncMethod = new StubMethodDescriptor("GetPublishListAsync",
    com.xty.dubbo_gen.video.PublishListRequest.class, com.xty.dubbo_gen.video.PublishListResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.PublishListRequest::parseFrom,
    com.xty.dubbo_gen.video.PublishListResponse::parseFrom);

    /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
    private static final StubMethodDescriptor getWorkCountMethod = new StubMethodDescriptor("GetWorkCount",
    com.xty.dubbo_gen.video.GetWorkCountRequest.class, com.xty.dubbo_gen.video.GetWorkCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.GetWorkCountRequest::parseFrom,
    com.xty.dubbo_gen.video.GetWorkCountResponse::parseFrom);

    private static final StubMethodDescriptor getWorkCountAsyncMethod = new StubMethodDescriptor("GetWorkCount",
    com.xty.dubbo_gen.video.GetWorkCountRequest.class, java.util.concurrent.CompletableFuture.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.GetWorkCountRequest::parseFrom,
    com.xty.dubbo_gen.video.GetWorkCountResponse::parseFrom);

    private static final StubMethodDescriptor getWorkCountProxyAsyncMethod = new StubMethodDescriptor("GetWorkCountAsync",
    com.xty.dubbo_gen.video.GetWorkCountRequest.class, com.xty.dubbo_gen.video.GetWorkCountResponse.class, serviceDescriptor, MethodDescriptor.RpcType.UNARY,
    obj -> ((Message) obj).toByteArray(), obj -> ((Message) obj).toByteArray(), com.xty.dubbo_gen.video.GetWorkCountRequest::parseFrom,
    com.xty.dubbo_gen.video.GetWorkCountResponse::parseFrom);





    public static class VideoServiceStub implements VideoService{
        private final Invoker<VideoService> invoker;

        public VideoServiceStub(Invoker<VideoService> invoker) {
            this.invoker = invoker;
        }

            /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.video.FeedResponse getVideoFeed(com.xty.dubbo_gen.video.FeedRequest request){
            return StubInvocationUtil.unaryCall(invoker, getVideoFeedMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.video.FeedResponse> getVideoFeedAsync(com.xty.dubbo_gen.video.FeedRequest request){
            return StubInvocationUtil.unaryCall(invoker, getVideoFeedAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
        @Override
        public void getVideoFeed(com.xty.dubbo_gen.video.FeedRequest request, StreamObserver<com.xty.dubbo_gen.video.FeedResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getVideoFeedMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.video.PublishActionResponse publishAction(com.xty.dubbo_gen.video.PublishActionRequest request){
            return StubInvocationUtil.unaryCall(invoker, publishActionMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.video.PublishActionResponse> publishActionAsync(com.xty.dubbo_gen.video.PublishActionRequest request){
            return StubInvocationUtil.unaryCall(invoker, publishActionAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频作者信息
         * </pre>
         */
        @Override
        public void publishAction(com.xty.dubbo_gen.video.PublishActionRequest request, StreamObserver<com.xty.dubbo_gen.video.PublishActionResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, publishActionMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.video.PublishListResponse getPublishList(com.xty.dubbo_gen.video.PublishListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getPublishListMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.video.PublishListResponse> getPublishListAsync(com.xty.dubbo_gen.video.PublishListRequest request){
            return StubInvocationUtil.unaryCall(invoker, getPublishListAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频播放地址
         * </pre>
         */
        @Override
        public void getPublishList(com.xty.dubbo_gen.video.PublishListRequest request, StreamObserver<com.xty.dubbo_gen.video.PublishListResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getPublishListMethod , request, responseObserver);
        }
            /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
        @Override
        public com.xty.dubbo_gen.video.GetWorkCountResponse getWorkCount(com.xty.dubbo_gen.video.GetWorkCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getWorkCountMethod, request);
        }

        public CompletableFuture<com.xty.dubbo_gen.video.GetWorkCountResponse> getWorkCountAsync(com.xty.dubbo_gen.video.GetWorkCountRequest request){
            return StubInvocationUtil.unaryCall(invoker, getWorkCountAsyncMethod, request);
        }

            /**
         * <pre>
         *  视频封面地址
         * </pre>
         */
        @Override
        public void getWorkCount(com.xty.dubbo_gen.video.GetWorkCountRequest request, StreamObserver<com.xty.dubbo_gen.video.GetWorkCountResponse> responseObserver){
            StubInvocationUtil.unaryCall(invoker, getWorkCountMethod , request, responseObserver);
        }



    }

    public static abstract class VideoServiceImplBase implements VideoService, ServerService<VideoService> {

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
        public final Invoker<VideoService> getInvoker(URL url) {
            PathResolver pathResolver = url.getOrDefaultFrameworkModel()
            .getExtensionLoader(PathResolver.class)
            .getDefaultExtension();
            Map<String,StubMethodHandler<?, ?>> handlers = new HashMap<>();

            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetVideoFeed" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetVideoFeedAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/PublishAction" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/PublishActionAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetPublishList" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetPublishListAsync" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetWorkCount" );
            pathResolver.addNativeStub( "/" + SERVICE_NAME + "/GetWorkCountAsync" );

            BiConsumer<com.xty.dubbo_gen.video.FeedRequest, StreamObserver<com.xty.dubbo_gen.video.FeedResponse>> getVideoFeedFunc = this::getVideoFeed;
            handlers.put(getVideoFeedMethod.getMethodName(), new UnaryStubMethodHandler<>(getVideoFeedFunc));
            BiConsumer<com.xty.dubbo_gen.video.FeedRequest, StreamObserver<com.xty.dubbo_gen.video.FeedResponse>> getVideoFeedAsyncFunc = syncToAsync(this::getVideoFeed);
            handlers.put(getVideoFeedProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getVideoFeedAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.video.PublishActionRequest, StreamObserver<com.xty.dubbo_gen.video.PublishActionResponse>> publishActionFunc = this::publishAction;
            handlers.put(publishActionMethod.getMethodName(), new UnaryStubMethodHandler<>(publishActionFunc));
            BiConsumer<com.xty.dubbo_gen.video.PublishActionRequest, StreamObserver<com.xty.dubbo_gen.video.PublishActionResponse>> publishActionAsyncFunc = syncToAsync(this::publishAction);
            handlers.put(publishActionProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(publishActionAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.video.PublishListRequest, StreamObserver<com.xty.dubbo_gen.video.PublishListResponse>> getPublishListFunc = this::getPublishList;
            handlers.put(getPublishListMethod.getMethodName(), new UnaryStubMethodHandler<>(getPublishListFunc));
            BiConsumer<com.xty.dubbo_gen.video.PublishListRequest, StreamObserver<com.xty.dubbo_gen.video.PublishListResponse>> getPublishListAsyncFunc = syncToAsync(this::getPublishList);
            handlers.put(getPublishListProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getPublishListAsyncFunc));
            BiConsumer<com.xty.dubbo_gen.video.GetWorkCountRequest, StreamObserver<com.xty.dubbo_gen.video.GetWorkCountResponse>> getWorkCountFunc = this::getWorkCount;
            handlers.put(getWorkCountMethod.getMethodName(), new UnaryStubMethodHandler<>(getWorkCountFunc));
            BiConsumer<com.xty.dubbo_gen.video.GetWorkCountRequest, StreamObserver<com.xty.dubbo_gen.video.GetWorkCountResponse>> getWorkCountAsyncFunc = syncToAsync(this::getWorkCount);
            handlers.put(getWorkCountProxyAsyncMethod.getMethodName(), new UnaryStubMethodHandler<>(getWorkCountAsyncFunc));




            return new StubInvoker<>(this, url, VideoService.class, handlers);
        }


        @Override
        public com.xty.dubbo_gen.video.FeedResponse getVideoFeed(com.xty.dubbo_gen.video.FeedRequest request){
            throw unimplementedMethodException(getVideoFeedMethod);
        }

        @Override
        public com.xty.dubbo_gen.video.PublishActionResponse publishAction(com.xty.dubbo_gen.video.PublishActionRequest request){
            throw unimplementedMethodException(publishActionMethod);
        }

        @Override
        public com.xty.dubbo_gen.video.PublishListResponse getPublishList(com.xty.dubbo_gen.video.PublishListRequest request){
            throw unimplementedMethodException(getPublishListMethod);
        }

        @Override
        public com.xty.dubbo_gen.video.GetWorkCountResponse getWorkCount(com.xty.dubbo_gen.video.GetWorkCountRequest request){
            throw unimplementedMethodException(getWorkCountMethod);
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
