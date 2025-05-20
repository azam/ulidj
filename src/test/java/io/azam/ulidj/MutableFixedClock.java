/**
 * MIT License
 *
 * Copyright (c) 2016-2025 Azamshul Azizy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.azam.ulidj;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MutableFixedClock extends Clock {
  private final ReentrantReadWriteLock lock;
  private Clock fixed;

  public MutableFixedClock() {
    this(Instant.ofEpochSecond(0, 0), ZoneOffset.UTC);
  }

  public MutableFixedClock(Instant instant, ZoneId zone) {
    this.lock = new ReentrantReadWriteLock();
    this.fixed = Clock.fixed(instant, zone);
  }

  @Override
  public ZoneId getZone() {
    Lock l = this.lock.readLock();
    try {
      l.lock();
      return this.fixed.getZone();
    } finally {
      l.unlock();
    }
  }

  @Override
  public Clock withZone(ZoneId zone) {
    Lock l = this.lock.readLock();
    try {
      l.lock();
      return new MutableFixedClock(this.fixed.instant(), zone);
    } finally {
      l.unlock();
    }
  }

  @Override
  public Instant instant() {
    Lock l = this.lock.readLock();
    try {
      l.lock();
      return this.fixed.instant();
    } finally {
      l.unlock();
    }
  }

  public void tick(TemporalAmount amount) {
    Lock l = this.lock.writeLock();
    try {
      l.lock();
      this.fixed = Clock.fixed(this.fixed.instant().plus(amount), this.fixed.getZone());
    } finally {
      l.unlock();
    }
  }
}
