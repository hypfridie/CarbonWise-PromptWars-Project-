package com.carbonwise.app.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AiChatResponse {

    @SerializedName("candidates")
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() { return candidates; }
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }

    public String getText() {
        if (candidates != null && !candidates.isEmpty()) {
            Candidate candidate = candidates.get(0);
            if (candidate.content != null && candidate.content.parts != null && !candidate.content.parts.isEmpty()) {
                return candidate.content.parts.get(0).text;
            }
        }
        return "I'm sorry, I couldn't process that request.";
    }

    public static class Candidate {
        @SerializedName("content")
        public Content content;
        @SerializedName("finishReason")
        public String finishReason;
    }

    public static class Content {
        @SerializedName("parts")
        public List<Part> parts;
        @SerializedName("role")
        public String role;
    }

    public static class Part {
        @SerializedName("text")
        public String text;
    }
}
