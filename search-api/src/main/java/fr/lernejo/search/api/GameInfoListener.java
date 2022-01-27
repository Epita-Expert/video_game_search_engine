package fr.lernejo.search.api;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public record GameInfoListener(RestHighLevelClient client) {

    @RabbitListener(queues = "#{queue.getName()}")
    public void onMessage(String message, @Header("game_id") String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest("games").id(id).source(message, XContentType.JSON);

//        this.client.get(new GetIndexRequest("games"), RequestOptions.DEFAULT);
//        if (request)

        this.client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(message);
        System.out.println(id);
    }
}
