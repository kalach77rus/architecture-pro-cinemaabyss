package ru.kalach.events.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        return handle(exception, consumer);
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        handle(exception, consumer);
    }

    private boolean handle(Exception exception, Consumer<?, ?> consumer) {
        log.error("Exception thrown", exception);
        if (exception instanceof RecordDeserializationException ex) {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
            return true;
        } else {
            log.error("Exception not handled", exception);
        }
        return false;
    }
}
