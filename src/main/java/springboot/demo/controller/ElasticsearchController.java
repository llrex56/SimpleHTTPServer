package springboot.demo.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
//@RequestMapping("/es")
public class ElasticsearchController {

    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/get/book/novel")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
        if (id.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("book", "novel", id).get();

        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }

    @PostMapping("/add/book/novel")
    @ResponseBody
    public ResponseEntity add(
            @RequestParam(name = "author") String author,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "publishDate")
                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                    Date publishDate) {

        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("author", author)
                    .field("name", name)
                    .field("publishDate", publishDate)
                    .endObject();

            IndexResponse result = this.client.prepareIndex("book", "novel").setSource(content).get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/book/novel")
    @ResponseBody
    public ResponseEntity delete(@RequestParam(name = "id", required = true) String id) {
        DeleteResponse result = this.client.prepareDelete("book", "novel", id).get();
        return new ResponseEntity(result.getId(), HttpStatus.OK);
    }

    @PutMapping("update/book/novel")
    @ResponseBody
    public ResponseEntity update(
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "publishDate")
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date publishDate) {

        UpdateRequest update = new UpdateRequest("book", "novel", id);

        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject();

            if (!StringUtils.isEmpty(name)) {
                content.field("name", name);
            }
            if (!StringUtils.isEmpty(author)) {
                content.field("author", author);
            }
            if (publishDate != null) {
                content.field("publishDate", publishDate);
            }
            content.endObject();
            update.doc(content);

            UpdateResponse result = this.client.update(update).get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("query/book/novel")
    @ResponseBody
    public ResponseEntity query(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "name", required = false) String name) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (author != null) {
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if (name != null) {
            boolQuery.must(QueryBuilders.matchQuery("name", name));
        }
        SearchRequestBuilder builder = this.client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println(builder);

        SearchResponse response = builder.get();

        List<Object> result = new ArrayList<>();

        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}