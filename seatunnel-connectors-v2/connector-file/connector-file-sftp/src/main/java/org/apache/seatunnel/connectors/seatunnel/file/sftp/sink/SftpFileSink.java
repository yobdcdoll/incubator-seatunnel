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

package org.apache.seatunnel.connectors.seatunnel.file.sftp.sink;

import org.apache.seatunnel.api.common.PrepareFailException;
import org.apache.seatunnel.api.sink.SeaTunnelSink;
import org.apache.seatunnel.common.config.CheckConfigUtil;
import org.apache.seatunnel.common.config.CheckResult;
import org.apache.seatunnel.common.constants.PluginType;
import org.apache.seatunnel.connectors.seatunnel.file.config.FileSystemType;
import org.apache.seatunnel.connectors.seatunnel.file.sftp.config.SftpConf;
import org.apache.seatunnel.connectors.seatunnel.file.sftp.config.SftpConfig;
import org.apache.seatunnel.connectors.seatunnel.file.sink.BaseFileSink;

import org.apache.seatunnel.shade.com.typesafe.config.Config;

import com.google.auto.service.AutoService;

@AutoService(SeaTunnelSink.class)
public class SftpFileSink extends BaseFileSink {
    @Override
    public String getPluginName() {
        return FileSystemType.SFTP.getFileSystemPluginName();
    }

    @Override
    public void prepare(Config pluginConfig) throws PrepareFailException {
        CheckResult result = CheckConfigUtil.checkAllExists(pluginConfig,
                SftpConfig.SFTP_HOST.key(), SftpConfig.SFTP_PORT.key(),
                SftpConfig.SFTP_USERNAME.key(), SftpConfig.SFTP_PASSWORD.key());
        if (!result.isSuccess()) {
            throw new PrepareFailException(getPluginName(), PluginType.SINK, result.getMsg());
        }
        super.prepare(pluginConfig);
        hadoopConf = SftpConf.buildWithConfig(pluginConfig);
    }
}
