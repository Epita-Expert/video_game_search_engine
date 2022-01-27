package fr.lernejo.search.api;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/games")
public record GameController(RestHighLevelClient client) {
    @GetMapping
    public Stream search(
        @RequestParam(name="query") String queryString,
        @RequestParam(name="source", defaultValue = "6") String source ) throws IOException {
        QueryStringQueryBuilder query = new QueryStringQueryBuilder(queryString);
        SearchRequest request = new SearchRequest("games").source(new SearchSourceBuilder().query(query).size(Integer.parseInt(source)));
        SearchResponse result = this.client.search(request, RequestOptions.DEFAULT);
        return StreamSupport.stream(result.getHits().spliterator(), false).map((g) -> g.getSourceAsMap());
    }

}
