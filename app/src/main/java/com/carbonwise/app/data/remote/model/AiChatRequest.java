package com.carbonwise.app.data.remote.model;

import java.util.ArrayList;
import java.util.List;

public class AiChatRequest {

    private List<Content> contents;

    public AiChatRequest() {
        this.contents = new ArrayList<>();
    }

    public AiChatRequest(String message) {
        this();
        Content content = new Content();
        content.role = "user";
        content.parts = new ArrayList<>();
        Part part = new Part();
        part.text = message;
        content.parts.add(part);
        this.contents.add(content);
    }

    public List<Content> getContents() { return contents; }
    public void setContents(List<Content> contents) { this.contents = contents; }

    public static class Content {
        public String role;
        public List<Part> parts;
    }

    public static class Part {
        public String text;
    }
}
