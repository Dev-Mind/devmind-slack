package com.devmind.slack;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 28/01/16.
 */
public class SlackMessage {

    private String channel;
    private String content;
    private String username;
    private String emoji;
    private String image;

    public String getChannel() {
        return channel;
    }

    public SlackMessage setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SlackMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImage() {
        return image;
    }

    public SlackMessage setImage(String image) {
        this.image = image;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SlackMessage setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmoji() {
        return emoji;
    }

    public SlackMessage setEmoji(String emoji) {
        this.emoji = emoji;
        return this;
    }
}
