/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.connectors.seatunnel.elasticsearch.dto;

import org.apache.seatunnel.connectors.seatunnel.elasticsearch.config.SinkConfig;

import org.apache.seatunnel.shade.com.typesafe.config.Config;

import lombok.Data;

/**
 * index config by seatunnel
 */
@Data
public class IndexInfo {

    private String index;
    private String type;

    public IndexInfo(Config pluginConfig) {
        index = pluginConfig.getString(SinkConfig.INDEX.key());
        if (pluginConfig.hasPath(SinkConfig.INDEX_TYPE.key())) {
            type = pluginConfig.getString(SinkConfig.INDEX_TYPE.key());
        }
    }

    public IndexInfo(String index, String type) {
        this.index = index;
        this.type = type;
    }
}
