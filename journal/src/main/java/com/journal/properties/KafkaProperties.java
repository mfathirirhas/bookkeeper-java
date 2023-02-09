package com.journal.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "journal.kafka")
@Component
public class KafkaProperties {

    private String address;
    private String dayBookTopic;
    private String dayBookKey;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getDayBookTopic() {
        return dayBookTopic;
    }

    public void setDayBookTopic(String dayBookTopic) {
        this.dayBookTopic = dayBookTopic;
    }

    public String getDayBookKey() {
        return dayBookKey;
    }

    public void setDayBookKey(String dayBookKey) {
        this.dayBookKey = dayBookKey;
    }
}
