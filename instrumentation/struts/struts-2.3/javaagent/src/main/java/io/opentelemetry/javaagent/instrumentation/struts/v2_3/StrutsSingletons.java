/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.struts.v2_3;

import com.opensymphony.xwork2.ActionInvocation;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.instrumentation.api.incubator.semconv.code.CodeAttributesExtractor;
import io.opentelemetry.instrumentation.api.incubator.semconv.code.CodeSpanNameExtractor;
import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import io.opentelemetry.javaagent.bootstrap.internal.ExperimentalConfig;

public class StrutsSingletons {
  private static final String INSTRUMENTATION_NAME = "io.opentelemetry.struts-2.3";

  private static final Instrumenter<ActionInvocation, Void> INSTRUMENTER;

  static {
    StrutsCodeAttributesGetter codeAttributesGetter = new StrutsCodeAttributesGetter();

    INSTRUMENTER =
        Instrumenter.<ActionInvocation, Void>builder(
                GlobalOpenTelemetry.get(),
                INSTRUMENTATION_NAME,
                CodeSpanNameExtractor.create(codeAttributesGetter))
            .addAttributesExtractor(CodeAttributesExtractor.create(codeAttributesGetter))
            .setEnabled(ExperimentalConfig.get().controllerTelemetryEnabled())
            .buildInstrumenter();
  }

  public static Instrumenter<ActionInvocation, Void> instrumenter() {
    return INSTRUMENTER;
  }

  private StrutsSingletons() {}
}
