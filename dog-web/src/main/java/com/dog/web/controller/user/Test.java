package com.dog.web.controller.user;

//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
//import org.elasticsearch.client.transport.TransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;

//http://blog.csdn.net/wwd0501/article/details/78502004
public class Test {
//
//    private TransportClient client;
//
//    protected void query() throws Throwable {
//
//        client = EngineClient.getConnection();
//        String index = "cars";
//        String type = "transactions";
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type);
//        TermsAggregationBuilder field1Terms = AggregationBuilders.terms("group_by_field1").field("field1").size(200);
//        SumAggregationBuilder fieldSum2 = AggregationBuilders.sum("sum_field2").field("field2");
//        SumAggregationBuilder fieldSum3 = AggregationBuilders.sum("sum_field3").field("field3");
//        field1Terms.subAggregation(fieldSum2).subAggregation(fieldSum3);
//
//        searchRequestBuilder.addAggregation(field1Terms);
//
//    }
}
