/*
 *
 *  Copyright 2016 Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package io.github.robwin.consumer;

import io.github.robwin.circularbuffer.CircularFifoBuffer;
import io.github.robwin.circularbuffer.ConcurrentCircularFifoBuffer;
import io.reactivex.functions.Consumer;
import javaslang.collection.List;

/**
 * A RxJava consumer which stores CircuitBreakerEvents in a circular buffer with a fixed capacity.
 */
public class CircularEventConsumer<T> implements Consumer<T>{

    private CircularFifoBuffer<T> eventCircularFifoBuffer;

    /**
     * Creates an {@code CircuitBreakerEventConsumer} with the given (fixed)
     * capacity
     *
     * @param capacity the capacity of this CircuitBreakerEventConsumer
     * @throws IllegalArgumentException if {@code capacity < 1}
     */
    public CircularEventConsumer(int capacity) {
        this.eventCircularFifoBuffer = new ConcurrentCircularFifoBuffer<>(capacity);
    }

    @Override
    public void accept(T event) throws Exception {
        eventCircularFifoBuffer.add(event);
    }

    /**
     * Returns a list containing all of the buffered events.
     *
     * @return a list containing all of the buffered events.
     */
    public List<T> getBufferedEvents(){
        return eventCircularFifoBuffer.toList();
    }
}
