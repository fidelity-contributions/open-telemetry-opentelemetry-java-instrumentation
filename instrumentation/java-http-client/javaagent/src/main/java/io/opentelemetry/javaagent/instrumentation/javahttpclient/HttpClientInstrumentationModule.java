/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.javahttpclient;

import static java.util.Arrays.asList;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.extension.instrumentation.InstrumentationModule;
import io.opentelemetry.javaagent.extension.instrumentation.TypeInstrumentation;
import io.opentelemetry.javaagent.extension.instrumentation.internal.ExperimentalInstrumentationModule;
import java.util.List;

@AutoService(InstrumentationModule.class)
public class HttpClientInstrumentationModule extends InstrumentationModule
    implements ExperimentalInstrumentationModule {
  public HttpClientInstrumentationModule() {
    super("java-http-client");
  }

  @Override
  public List<TypeInstrumentation> typeInstrumentations() {
    return asList(new HttpClientInstrumentation(), new HttpHeadersInstrumentation());
  }

  @Override
  public boolean isIndyReady() {
    return true;
  }
}
