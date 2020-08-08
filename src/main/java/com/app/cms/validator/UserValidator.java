package com.app.cms.validator;

import com.app.cms.entity.User;
import com.app.cms.error.type.LoginIsInUseException;
import com.app.cms.error.type.ObjectHaveReferencedObjects;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements ValidatorOnSave<User>, ValidatorOnDelete {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public UserValidator(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void validateOnSave(User user) {
        if (user.getId() == null) {
            validationOnCreation(user);
        } else {
            validationOnUpdate(user);
        }
    }

    @Override
    public void validateOnDelete(Long userId) {
        if (articleRepository.existsByUserId(userId)) {
            var validationError = new ValidationError()
                    .appendDetail("article", "User has articles, delete them first");

            throw new ObjectHaveReferencedObjects(validationError.toString());
        }
    }

    private void validationOnUpdate(User user) {
        if (user.getLogin() != null && userRepository.existsByLoginAndIdNot(user.getLogin().getValue(), user.getId())) {
            throwLoginIsInUseException();
        }
    }

    private void validationOnCreation(User user) {
        if (userRepository.existsByLogin(user.getLogin().getValue())) {
            throwLoginIsInUseException();
        }
    }

    private void throwLoginIsInUseException() {
        var validationError = new ValidationError()
                .appendDetail("login", "User login is already in use");

        throw new LoginIsInUseException(validationError.toString());
    }
}
