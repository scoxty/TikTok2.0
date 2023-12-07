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
import com.google.protobuf.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.concurrent.CompletableFuture;

public interface FavoriteService {

    String JAVA_SERVICE_NAME = "com.xty.dubbo_gen.favorite.FavoriteService";
    String SERVICE_NAME = "douyin.extra.first.FavoriteService";

        /**
         * <pre>
         *  视频唯一标识
         * </pre>
         */
    com.xty.dubbo_gen.favorite.FavoriteActionResponse favoriteAction(com.xty.dubbo_gen.favorite.FavoriteActionRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.FavoriteActionResponse> favoriteActionAsync(com.xty.dubbo_gen.favorite.FavoriteActionRequest request){
        return CompletableFuture.completedFuture(favoriteAction(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void favoriteAction(com.xty.dubbo_gen.favorite.FavoriteActionRequest request, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteActionResponse> responseObserver){
        favoriteActionAsync(request).whenComplete((r, t) -> {
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
    com.xty.dubbo_gen.favorite.FavoriteListResponse getFavoriteList(com.xty.dubbo_gen.favorite.FavoriteListRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.FavoriteListResponse> getFavoriteListAsync(com.xty.dubbo_gen.favorite.FavoriteListRequest request){
        return CompletableFuture.completedFuture(getFavoriteList(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getFavoriteList(com.xty.dubbo_gen.favorite.FavoriteListRequest request, StreamObserver<com.xty.dubbo_gen.favorite.FavoriteListResponse> responseObserver){
        getFavoriteListAsync(request).whenComplete((r, t) -> {
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
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getVideoFavoriteCount(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getVideoFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request){
        return CompletableFuture.completedFuture(getVideoFavoriteCount(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getVideoFavoriteCount(com.xty.dubbo_gen.favorite.GetVideoFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
        getVideoFavoriteCountAsync(request).whenComplete((r, t) -> {
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
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserFavoriteCount(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getUserFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request){
        return CompletableFuture.completedFuture(getUserFavoriteCount(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getUserFavoriteCount(com.xty.dubbo_gen.favorite.GetUserFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
        getUserFavoriteCountAsync(request).whenComplete((r, t) -> {
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
         *  视频的点赞总数
         * </pre>
         */
    com.xty.dubbo_gen.favorite.GetFavoriteCountResponse getUserTotalFavoriteCount(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> getUserTotalFavoriteCountAsync(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request){
        return CompletableFuture.completedFuture(getUserTotalFavoriteCount(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void getUserTotalFavoriteCount(com.xty.dubbo_gen.favorite.GetUserTotalFavoriteCountRequest request, StreamObserver<com.xty.dubbo_gen.favorite.GetFavoriteCountResponse> responseObserver){
        getUserTotalFavoriteCountAsync(request).whenComplete((r, t) -> {
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
         *  视频的评论总数
         * </pre>
         */
    com.xty.dubbo_gen.favorite.IsUserFavoriteResponse isUserFavorite(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request);

    default CompletableFuture<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse> isUserFavoriteAsync(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request){
        return CompletableFuture.completedFuture(isUserFavorite(request));
    }

    /**
    * This server stream type unary method is <b>only</b> used for generated stub to support async unary method.
    * It will not be called if you are NOT using Dubbo3 generated triple stub and <b>DO NOT</b> implement this method.
    */
    default void isUserFavorite(com.xty.dubbo_gen.favorite.IsUserFavoriteRequest request, StreamObserver<com.xty.dubbo_gen.favorite.IsUserFavoriteResponse> responseObserver){
        isUserFavoriteAsync(request).whenComplete((r, t) -> {
            if (t != null) {
                responseObserver.onError(t);
            } else {
                responseObserver.onNext(r);
                responseObserver.onCompleted();
            }
        });
    }






}
