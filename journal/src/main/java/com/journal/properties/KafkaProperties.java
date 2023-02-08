package com.journal.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "journal.kafka")
@Component
public class KafkaProperties {

    private String address;
    private String daybookTopic;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getDaybookTopic() {
        return daybookTopic;
    }

    public void setDaybookTopic(String daybookTopic) {
        this.daybookTopic = daybookTopic;
    }
}
