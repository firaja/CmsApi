package com.app.cms.repository;

import java.util.Map;

public interface UserRepositoryConcreteMethods {
    void updatePartially(long userId, Map<String, Object> fields);
}
