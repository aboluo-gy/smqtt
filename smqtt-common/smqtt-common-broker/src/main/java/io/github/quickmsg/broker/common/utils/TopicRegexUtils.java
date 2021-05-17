package io.github.quickmsg.broker.common.utils;

/**
 * @Author luxurong
 */
public class TopicRegexUtils {

    public static String regexTopic(String topic) {
        if (topic.startsWith("$")) {
            topic = "\\" + topic;
        }
        return topic
                .replaceAll("/", "\\\\/")
                .replaceAll("\\+", "[^/]+")
                .replaceAll("#", "(.+)") + "$";
    }


}
