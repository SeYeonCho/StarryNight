package com.ssafy.starry.common.utils.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RSSFeedParser {

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String PUBDATE = "pubdate";
    static final String TRAFFIC = "approx_traffic";
    static final String NEWS_ITEM_TITLE = "news_item_title";
    static final String NEWS_ITEM_SNIPPET = "news_item_snippet";
    static final String NEWS_ITEM_URL = "news_item_url";
    static final String NEWS_ITEM_SOURCE = "news_item_source";


    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String pubdate = "";
            String traffic = "";
            StringBuilder news_title = new StringBuilder();
            String news_url = "";
            String news_source = "";
            StringBuilder sb = new StringBuilder();

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                        .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language,
                                    copyright, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                        case PUBDATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case TRAFFIC:
                            traffic = getCharacterData(event, eventReader);
                            break;
                        case NEWS_ITEM_TITLE:
                            news_title = new StringBuilder(getCharacterData(event, eventReader));
                            break;
                        case NEWS_ITEM_SOURCE:
                            news_source = getCharacterData(event, eventReader);
                            break;
                        case NEWS_ITEM_URL:
                            news_url = getCharacterData(event, eventReader);
                            break;

                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setDescription(description);
                        message.setLink(link);
                        message.setTitle(title);
                        message.setPubdate(pubdate);
                        message.setNews_source(news_source);
                        message.setNews_title(
                            news_title.toString().replaceAll("(\r\n|\r|\n|\n\r|\\p{Z}|\\t)", "")
                                .replaceAll("&quot;", "").replaceAll("&#39;", "")
                                .replaceAll("&nbsp;", "").replaceAll("&amp;", "")
                                .replaceAll("&lt;", "").replaceAll("&gt;", "")
                                .replaceAll("&quot;", "").replaceAll("#39;", ""));
                        message.setNews_url(news_url);
                        message.setTraffic(traffic);
                        assert feed != null;
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                    } else {
                        String localPart = event.asEndElement().getName()
                            .getLocalPart();
                        if (NEWS_ITEM_TITLE.equals(localPart)) {
                            news_title.append(sb.toString());
                        }
                    }
                    sb.setLength(0);
                } else {
                    if (event.isCharacters()) {
                        sb.append(event.asCharacters().getData());
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
        throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

