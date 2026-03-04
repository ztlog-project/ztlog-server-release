package com.devlog.admin.dto.stats.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GiscusResponse {
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private Repository repository;
    }

    @Getter
    @Setter
    public static class Repository {
        private Discussions discussions;
    }

    @Getter
    @Setter
    public static class Discussions {
        private List<Node> nodes;
    }

    @Getter
    @Setter
    public static class Node {
        private String title;      // 예: "http://ztlog.io/contents/10"
        private Comments comments; // 댓글 정보
    }

    @Getter
    @Setter
    public static class Comments {
        private int totalCount;    // 실제 저장할 댓글 수
    }
}
