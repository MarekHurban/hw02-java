package cz.muni.fi.pb162.hw02.crawler;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakub Cechacek
 */
public interface ReverseIndexCrawler {
    /**
     * Starts crawling at given URL and builds a reverse index (same as {@link #reverseIndex(Map)}
     * @param url URL to be crawled
     * @return a map of urls and their references
     */
    Map<String, List<String>> crawlReverse(String url);

    /**
     * Builds a reverse index of URLs and links containing a reference to it
     * @param index a map of URL and links contained by it (as returned by {@link  IndexCrawler#crawlAll(String)}
     * @return a map of urls and their references
     */
    Map<String, List<String>> reverseIndex(Map<String, List<String>> index);
}
