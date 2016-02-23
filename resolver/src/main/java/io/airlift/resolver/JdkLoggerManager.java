/*
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
 */
package io.airlift.resolver;

import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.LoggerManager;

import static java.util.logging.Logger.getLogger;

/**
 * Use JDK logging as a backing for a Plexus {@link LoggerManager},
 * ignoring Plexus logger API parts that are not classical and probably not really used.
 * This is based on Slf4jLoggerManager by Json van Zyl.
 */
class JdkLoggerManager
        implements LoggerManager
{
    @Override
    public Logger getLoggerForComponent(String role)
    {
        return new JdkLogger(getLogger(role));
    }

    /**
     * The logger name for a component with a non-null hint is <code>role.hint</code>.
     * <b>Warning</b>: this does not conform to logger name as class name convention.
     * (and what about <code>null</code> and <code>default</code> hint equivalence?)
     */
    @Override
    public Logger getLoggerForComponent(String role, String hint)
    {
        return (null == hint
                ? getLoggerForComponent(role)
                : new JdkLogger(getLogger(role + '.' + hint)));
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void setThreshold(String role, int threshold)
    {
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void setThreshold(String role, String roleHint, int threshold)
    {
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public int getThreshold(String role)
    {
        return 0;
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public int getThreshold(String role, String roleHint)
    {
        return 0;
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void returnComponentLogger(String role)
    {
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void returnComponentLogger(String role, String hint)
    {
    }

    /**
     * <b>Warning</b>: ignored (always return <code>0</code>).
     */
    @Override
    public int getThreshold()
    {
        return 0;
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void setThreshold(int threshold)
    {
    }

    /**
     * <b>Warning</b>: ignored.
     */
    @Override
    public void setThresholds(int threshold)
    {
    }

    /**
     * <b>Warning</b>: ignored (always return <code>0</code>).
     */
    @Override
    public int getActiveLoggerCount()
    {
        return 0;
    }
}