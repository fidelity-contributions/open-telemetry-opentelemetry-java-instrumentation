package io.opentracing.contrib.agent.helper;

import io.opentracing.contrib.jms.TracingMessageProducer;
import org.jboss.byteman.rule.Rule;

import javax.jms.MessageProducer;

public class JMSMessageProducerHelper extends DDAgentTracingHelper<MessageProducer> {

	public JMSMessageProducerHelper(Rule rule) {
		super(rule);
	}

	@Override
	public MessageProducer patch(MessageProducer args) {
		return super.patch(args);
	}

	/**
	 * Strategy: Wrapper the instance into a new one.
	 *
	 * @param producer The JMS instance
	 * @return A new instance with the old one wrapped
	 * @throws Exception
	 */
	protected MessageProducer doPatch(MessageProducer producer) throws Exception {
		if (producer instanceof TracingMessageProducer) {
			return producer;
		}
		return new TracingMessageProducer(producer, tracer);
	}
}