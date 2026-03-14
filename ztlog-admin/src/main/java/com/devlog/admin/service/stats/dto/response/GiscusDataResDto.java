package com.devlog.admin.service.stats.dto.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class GiscusDataResDto {
    private GiscusData data;

    public List<Node> getNodes() {
        if (data != null && data.repository != null && data.repository.discussions != null) {
            return data.repository.discussions.nodes;
        }
        return Collections.emptyList();
    }

    @Data
    public static class GiscusData {
        private Repository repository;
    }

    @Data
    public static class Repository {
        private Discussions discussions;
    }

    @Data
    public static class Discussions {
        private List<Node> nodes;
    }

    @Data
    public static class Node {
        private String title;
        private Comments comments;
    }

    @Data
    public static class Comments {
        private int totalCount;
    }

}
