package cz.muni.fi.pb162.hw02.crawler;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakub Cechacek
 */
public interface IndexCrawler {
    /**
     * Scans a given URL and parses out all links
     *
     * @param url URL to be crawled
     * @return a list of URLs contained by given URL
     */
    List<String> crawl(String url);

    /**
     * Similar to {@link #crawl(String)} but follows the links
     * @param url URL to be crawled
     * @return a map of URL and links contained by it
     */
    Map<String, List<String>> crawlAll(String url);
}
