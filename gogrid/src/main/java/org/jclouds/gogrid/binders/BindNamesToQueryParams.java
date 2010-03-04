/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 */
package org.jclouds.gogrid.binders;

import org.jclouds.http.HttpRequest;
import org.jclouds.rest.Binder;
import org.jclouds.rest.internal.GeneratedHttpRequest;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import static org.jclouds.gogrid.reference.GoGridQueryParams.NAME_KEY;

/**
 * Binds names to corresponding query parameters

 * @author Oleksiy Yarmula
 */
public class BindNamesToQueryParams implements Binder {

    /**
     * Binds the names to query parameters. The pattern, as
     * specified by GoGrid's specification, is:
     *
     * https://api.gogrid.com/api/grid/server/get
     *      ?name=My+Server
     *      &name=My+Server+2
     *      &name=My+Server+3
     *      &name=My+Server+4
     * @param request
     *          request where the query params will be set
     * @param input array of String params
     */
    @Override
    public void bindToRequest(HttpRequest request, Object input) {
        checkArgument(checkNotNull(request, "request is null") instanceof GeneratedHttpRequest,
                "this binder is only valid for GeneratedHttpRequests!");
        checkArgument(checkNotNull(input, "input is null") instanceof String[],
                "this binder is only valid for String[] arguments");

        String[] names = (String[]) input;
        GeneratedHttpRequest generatedRequest = (GeneratedHttpRequest) request;

        for(String name : names) {
            generatedRequest.addQueryParam(NAME_KEY, name);
        }

    }
}
