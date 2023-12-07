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
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface VideoService {

    String JAVA_SERVICE_NAME = "com.xty.dubbo_gen.video.VideoService";
    String SERVICE_NAME = "douyin.core.VideoService";

        /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
    com.xty.dubbo_gen.video.FeedResponse getVideoFeed(com.xty.dubbo_gen.video.FeedRequest request);

    default CompletableFuture<com.xty.dubbo_gen.video.FeedResponse> getVideoFeedAsync(com.xty.dubbo_gen.video.FeedRequest request){
        return CompletableFuture.completedFuture(getVideoFeed(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getVideoFeed(com.xty.dubbo_gen.video.FeedRequest request, StreamObserver<com.xty.dubbo_gen.video.FeedResponse> responseObserver){
        getVideoFeedAsync(request).whenComplete((r, t) -> {
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
         *  视频作者信息
         * </pre>
         */
    com.xty.dubbo_gen.video.PublishActionResponse publishAction(com.xty.dubbo_gen.video.PublishActionRequest request);

    default CompletableFuture<com.xty.dubbo_gen.video.PublishActionResponse> publishActionAsync(com.xty.dubbo_gen.video.PublishActionRequest request){
        return CompletableFuture.completedFuture(publishAction(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void publishAction(com.xty.dubbo_gen.video.PublishActionRequest request, StreamObserver<com.xty.dubbo_gen.video.PublishActionResponse> responseObserver){
        publishActionAsync(request).whenComplete((r, t) -> {
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
         *  视频播放地址
         * </pre>
         */
    com.xty.dubbo_gen.video.PublishListResponse getPublishList(com.xty.dubbo_gen.video.PublishListRequest request);

    default CompletableFuture<com.xty.dubbo_gen.video.PublishListResponse> getPublishListAsync(com.xty.dubbo_gen.video.PublishListRequest request){
        return CompletableFuture.completedFuture(getPublishList(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getPublishList(com.xty.dubbo_gen.video.PublishListRequest request, StreamObserver<com.xty.dubbo_gen.video.PublishListResponse> responseObserver){
        getPublishListAsync(request).whenComplete((r, t) -> {
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
         *  视频封面地址
         * </pre>
         */
    com.xty.dubbo_gen.video.GetWorkCountResponse getWorkCount(com.xty.dubbo_gen.video.GetWorkCountRequest request);

    default CompletableFuture<com.xty.dubbo_gen.video.GetWorkCountResponse> getWorkCountAsync(com.xty.dubbo_gen.video.GetWorkCountRequest request){
        return CompletableFuture.completedFuture(getWorkCount(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getWorkCount(com.xty.dubbo_gen.video.GetWorkCountRequest request, StreamObserver<com.xty.dubbo_gen.video.GetWorkCountResponse> responseObserver){
        getWorkCountAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }






}
