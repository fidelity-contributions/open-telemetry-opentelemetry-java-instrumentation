/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.cassandra.v3_0;

import com.datastax.driver.core.Session;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CassandraRequest {

  public static CassandraRequest create(Session session, String queryText) {
    return new AutoValue_CassandraRequest(session, queryText);
  }

  public abstract Session getSession();

  public abstract String getQueryText();
}
