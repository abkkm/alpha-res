package huh.enterprise.alpha.vavr;

import huh.enterprise.alpha.component.food.model.Food;
import io.vavr.Predicates;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@Slf4j
@Ignore
public class TryTest {

    @Test
    public void shouldTrySuccess() {
        Try<List<Food>> result = Try.of(() -> businessFoodCall(TryResultCode.SUCCESS));
        assertThat(result.get().size(), is(2));
    }

    @Test(expected = BusinessException.class)
    public void shouldTryFail() {
        Try<List<Food>> result = Try.of(() -> businessFoodCall(TryResultCode.FAILED));
        assertThat(result.get().size(), is(2));
    }

    @Test
    public void example2() {
        List<Food> foods = List.of(
                Food.builder().name("food3").build(),
                Food.builder().name("food4").build());

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.SUCCESS))
                .flatMap(results -> Try.success(results.stream()
                        .filter(result -> result.getName().equals("food1"))
                        .collect(toList())))
                .map(result -> Stream.of(foods, result).flatMap(List::stream).collect(toList()))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(result -> System.out.println("FAILURE"));

        apiResult.get();
    }

    // Verify flatmap is not run if failed
    @Test(expected = BusinessException2.class)
    public void example3() {
        List<Food> foods = List.of(
                Food.builder().name("food3").build(),
                Food.builder().name("food4").build());

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.FAILED))
                .flatMap(results -> Try.success(results.stream()
                        .filter(result -> result.getName().equals("food1"))
                        .collect(toList())))
                .map(result -> Stream.of(foods, result).flatMap(List::stream).collect(toList()))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(result -> throwBusinessException2())
                .recover(BusinessException.class, emptyList());

        fail();
    }

    // Should not hit onFailure
    @Test
    public void example4() {
        List<Food> foods = List.of(
                Food.builder().name("food3").build(),
                Food.builder().name("food4").build());

        List<Food> recoverFoods = List.of(Food.builder().name("recover").build());

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.FAILED))
                .flatMap(results -> Try.success(results.stream()
                        .filter(result -> result.getName().equals("food1"))
                        .collect(toList())))
                .map(result -> Stream.of(foods, result).flatMap(List::stream).collect(toList()))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(BusinessException2.class, e -> {
                    System.out.println("THIS IS NOT CALLED");
                    throwBusinessException2();
                })
                .recover(BusinessException.class, recoverFoods);

        var recover = apiResult.get();

        assertThat(recover.size(), is(1));
        assertThat(recover.get(0).getName(), is("recover"));
    }

    @Test(expected = BusinessException2.class)
    public void example5() {

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.FAILED))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(BusinessException.class, e -> {
                    throw new BusinessException2();
                })
                .get();

    }

    @SuppressWarnings("unchecked")
    @Test(expected = BusinessException2.class)
    public void example6() {

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.FAILED))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(RuntimeException.class, e -> log.error("FAILED ", e))
                .mapFailure(
                        Case($(Predicates.instanceOf(BusinessException2.class)), () -> new ServiceException2()),
                        Case($(Predicates.instanceOf(BusinessException.class)), e -> {
                            log.error("CASE MATCHED", e);
                            return new BusinessException2();
                        }),
                        Case($(), () -> new IllegalStateException()))
                .get();
    }

    @SuppressWarnings("unchecked")
    @Test(expected = BusinessException.class)
    public void example7() {

        var apiResult = Try.of(() -> businessFoodCall(TryResultCode.FAILED))
                .onSuccess(result -> System.out.printf("SUCCESS total size " + result.size() + result))
                .onFailure(RuntimeException.class, e -> log.error("FAILED ", e))
                .mapFailure(
                        Case($(Predicates.instanceOf(BusinessException2.class)), () -> new ServiceException2()))
                .get();
    }

    private List<Food> businessFoodCall(TryResultCode code) {
        if (code == TryResultCode.SUCCESS) {
            return List.of(Food.builder().name("food1").build(),
                    Food.builder().name("food2").build());
        }
        throw new BusinessException();
    }

    private void throwBusinessException2() {
        throw new BusinessException2();
    }

}
