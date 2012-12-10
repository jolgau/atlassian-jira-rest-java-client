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

import com.atlassian.jira.rest.client.domain.Session;
import com.atlassian.util.concurrent.Promise;

/**
 * Client handling the current user session
 *
 * @since v2.0.0
 */
public interface SessionRestClient {

	/**
	 * @return information about current session
	 * @throws RestClientException in case of problems (connectivity, malformed messages, etc.)
	 */
	Promise<Session> getCurrentSession() throws RestClientException;
}
