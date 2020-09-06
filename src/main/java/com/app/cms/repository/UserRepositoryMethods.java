package com.app.cms.repository;

import javax.transaction.Transactional;
import java.util.Map;


public interface UserRepositoryMethods {
    @Transactional
    void updatePartially(long userId, Map<String, Object> changedValues);
}
