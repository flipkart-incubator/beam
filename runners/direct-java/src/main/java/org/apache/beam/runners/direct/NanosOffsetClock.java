/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.runners.direct;

import java.time.Duration;
import org.joda.time.Instant;

/** A {@link Clock} that uses {@link System#nanoTime()} to track the progress of time. */
class NanosOffsetClock implements Clock {
  private final long baseMillis;
  private final long nanosAtBaseMillis;

  public static NanosOffsetClock create() {
    return new NanosOffsetClock();
  }

  private NanosOffsetClock() {
    baseMillis = System.currentTimeMillis();
    nanosAtBaseMillis = System.nanoTime();
  }

  @Override
  public Instant now() {
    return new Instant(
        baseMillis + Duration.ofNanos(System.nanoTime() - nanosAtBaseMillis).toMillis());
  }
}
