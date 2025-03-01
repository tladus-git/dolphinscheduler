/*
 * Licensed to Apache Software Foundation (ASF) under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Apache Software Foundation (ASF) licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.dolphinscheduler.registry.api;

import lombok.NonNull;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collection;

public interface Registry extends Closeable {

    /**
     * Connect to the registry, will wait in the given timeout
     *
     * @param timeout max timeout, if timeout <= 0 will wait indefinitely.
     * @throws RegistryException cannot connect in the given timeout
     */
    void connectUntilTimeout(@NonNull Duration timeout) throws RegistryException;

    boolean subscribe(String path, SubscribeListener listener);

    void unsubscribe(String path);

    void addConnectionStateListener(ConnectionListener listener);

    String get(String key);

    void put(String key, String value, boolean deleteOnDisconnect);

    void delete(String key);

    Collection<String> children(String key);

    boolean exists(String key);

    boolean acquireLock(String key);

    boolean releaseLock(String key);
}
