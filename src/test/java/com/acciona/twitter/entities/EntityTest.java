package com.acciona.twitter.entities;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.test.impl.GetterTester;
import org.junit.jupiter.api.Test;

public class EntityTest {

    @Test
    public void pojoValidator() {

        final Validator pojoValidator =
                ValidatorBuilder.create()
                        .with(new GetterMustExistRule())
                        .with(new GetterTester())
                        .with(new NoPrimitivesRule())
                        .with(new NoPublicFieldsExceptStaticFinalRule())
                        .build();

        pojoValidator.validate(PojoClassFactory.getPojoClass(HashtagEntity.class));
        pojoValidator.validate(PojoClassFactory.getPojoClass(TweetEntity.class));
        pojoValidator.validate(PojoClassFactory.getPojoClass(TwitterUserEntity.class));
    }
}