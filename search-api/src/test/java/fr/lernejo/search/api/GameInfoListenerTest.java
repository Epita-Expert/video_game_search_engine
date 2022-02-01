package fr.lernejo.search.api;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class GameInfoListenerTest {
    @Test
    void send_empty_message() {
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate template = springContext.getBean(RabbitTemplate.class);
            template.setMessageConverter(new Jackson2JsonMessageConverter());
            template.convertAndSend("", "game_info", "{}", m -> {
                m.getMessageProperties().setHeader("game_id", 1);
                return m;
            });
        }
    }
}
