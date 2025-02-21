package src.validation.util;

import java.util.function.Predicate;

public interface OrderStatusPredicate extends Predicate <String> {

    @Override
    boolean test(String status);

}
