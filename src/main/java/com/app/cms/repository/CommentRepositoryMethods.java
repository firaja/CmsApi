package com.app.cms.repository;

import java.util.Map;

public interface CommentRepositoryMethods {
    void updatePartially(long id, Map<String, Object> changedValues);
}
