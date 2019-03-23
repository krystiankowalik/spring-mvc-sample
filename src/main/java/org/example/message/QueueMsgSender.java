/* Sender Class wired with bean id="queueMsgSender" */

package org.example.message;

import javax.jms.*;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component(value = "queueMsgSender")
@Log4j
public class QueueMsgSender {

    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message, final String CorrId) {
       log.info("preparing to send queue jms message");

        jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage(message);
                msg.setJMSCorrelationID(CorrId);
                return msg;
            }
        });
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}