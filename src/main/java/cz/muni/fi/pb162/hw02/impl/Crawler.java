package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.SimpleHttpClient;
import cz.muni.fi.pb162.hw02.crawler.SmartCrawler;

import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Marek Hurban
 * Simple web crawler
 */
public class Crawler implements SmartCrawler {

    @Override
    public List<String> crawl(String url) {
        if (url == null) {
            return null;
        }
        SimpleHttpClient client = new SimpleHttpClient();
        try {
            String pageStr = client.get(url);
            return findUrls(pageStr);
        } catch (IOException e) {
            System.out.println("Server response error at:" + url);
            return null;
        }
    }

    @Override
    public Map<String, List<String>> crawlAll(String url) {
        if(url == null) {
            return null;
        }
        Queue<String> toVisit = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, List<String>> urlsMap = new HashMap<>();
        toVisit.add(url);
        // max 50 urls will be visited
        while(!toVisit.isEmpty() && visited.size() < 50) {
            String currentUrl = toVisit.poll();
            if(!visited.contains(currentUrl)) {
                List<String> foundUrls = new ArrayList<>(crawl(currentUrl));
                urlsMap.put(currentUrl, foundUrls);
                toVisit.addAll(foundUrls);
                visited.add(currentUrl);
            }
        }
        return urlsMap;
    }

    @Override
    public Map<String, List<String>> crawlReverse(String url) {
        return reverseIndex(crawlAll(url));
    }

    @Override
    public Map<String, List<String>> reverseIndex(Map<String, List<String>> index) {
        if (index == null){
            return null;
        }
        Map<String, List<String>> revMap = new HashMap<>();
        List<String> keys = new ArrayList<>(index.keySet());
        for(int i = 0; i < keys.size(); i++) {
            List<String> references = new ArrayList<>();
            for (Map.Entry<String, List<String>> miniMap : index.entrySet()) {
                if(miniMap.getValue().contains(keys.get(i))) {
                    references.add(miniMap.getKey());
                }
                // adds referenced urls that were not among keys in given index
                keys.addAll(miniMap.getValue().stream().distinct().filter(url -> !keys.contains(url))
                        .collect(Collectors.toSet()));
            }
            revMap.put(keys.get(i), references);
        }
        return revMap;
    }

    /**
     *
     * @param page to be search
     * @return list of found urls
     */
    private List<String> findUrls(String page) {
        // urlPattern is taken from 5. answer at
        // https://stackoverflow.com/questions/5713558/detect-and-extract-url-from-a-string
        final Pattern urlPattern = Pattern.compile("\\b((https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*" +
                "[-a-zA-Z0-9+&@#/%=~_|])");
        Matcher port = urlPattern.matcher(page);
        List<String> strings = new ArrayList<>();
        while (port.find()) {
            String toAdd = port.group();
            strings.add(toAdd);
        }
        return strings;
    }
}
