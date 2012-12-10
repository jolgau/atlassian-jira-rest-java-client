/*
 * Copyright (C) 2010 Atlassian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atlassian.jira.rest.client;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Basic exception which may be thrown by any remote operation encapsulated by the REST client.
 * Usually some more specific exception will be chained here and available via {@link #getCause()}
 *
 * @since v0.1
 */
public class RestClientException extends RuntimeException {

	private final Collection<String> errorMessages;
	private final Optional<Integer> statusCode;

	public RestClientException(Throwable cause) {
		super(cause);
		errorMessages = Collections.emptyList();
		this.statusCode = Optional.absent();
	}

	public RestClientException(Throwable cause, int statusCode) {
		super(cause);
		errorMessages = Collections.emptyList();
		this.statusCode = Optional.of(statusCode);
	}

	public RestClientException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorMessages = Arrays.asList(errorMessage);
		statusCode = Optional.absent();
	}

	/**
	 * @param errorMessages messages which will be joined with newline character and accessible then via {@link #getMessage()}
	 */
	public RestClientException(Collection<String> errorMessages, int statusCode) {
		super(Joiner.on("\n").join(errorMessages));
		this.errorMessages = new ArrayList<String>(errorMessages);
		this.statusCode = Optional.of(statusCode);
	}

	/**
	 * @param errorMessages messages which will be joined with newline character and accessible then via {@link #getMessage()}
	 * @param cause         the cause of this exception or <code>null</code>
	 */
	public RestClientException(Collection<String> errorMessages, Throwable cause, int statusCode) {
		super(Joiner.on("\n").join(errorMessages), cause);
		this.errorMessages = new ArrayList<String>(errorMessages);
		this.statusCode = Optional.of(statusCode);
	}

	/**
	 * @return error messages used while building this exception object
	 */
	public Iterable<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @return optional error code of failed http request.
	 */
	public Optional<Integer> getStatusCode() {
		return statusCode;
	}
}
