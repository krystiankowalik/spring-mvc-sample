package org.example.message;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component("msgListener")
@Log4j
public class MsgListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            try {
                log.info("Queue : " + message.getJMSDestination() + "Message recieved is : " + ((TextMessage) message).getText() + " CorrId : " + message.getJMSCorrelationID() + " JMSPriority : " + message.getJMSPriority());
                message.acknowledge();
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}