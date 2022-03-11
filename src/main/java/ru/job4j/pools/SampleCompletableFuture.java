package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626]
 * Примеры
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.03.2022
 */
public class SampleCompletableFuture {
    /**
     * Эмитация общей работы.
     *
     * @throws InterruptedException exception.
     */
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    /**
     * 1. Пример runAsync().
     * Создает асинхронную задачу, только действие без возврата результата.
     *
     * @return CompletableFuture.
     */
    public static CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("Сын: Мам/Пап, я пошел выносить мусор");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я вернулся!");
                });
    }

    /**
     * 1. Пример runAsync().
     * Выполняет асинхронную задачу, только действие без возврата результата.
     *
     * @throws Exception exception.
     */
    public static void runAsyncExample() throws Exception {
        CompletableFuture<Void> get = goToTrash();
        iWork();
    }

    /**
     * 2. Пример supplyAsync().
     * Создает асинхронную задачу, с возврата результата.
     *
     * @param product String.
     * @return CompletableFuture.
     */
    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пап, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product);
                    return product;
                });
    }

    /**
     * 2. Пример supplyAsync().
     * Выполняет асинхронную задачу, с возврата результата.
     *
     * @throws Exception exception.
     */
    public static void supplyAsyncExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        iWork();
        System.out.println("Куплено: " + bm.get());
    }

    /**
     * 3. Пример thenRun().
     * Этот метод ничего не возвращает,
     * а позволяет выполнить задачу типа Runnable
     * после выполнения асинхронной задачи.
     *
     * @throws Exception exception.
     */
    public static void thenRunExample() throws Exception {
        CompletableFuture<Void> gtt = goToTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: я мою руки");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки");
        });
        iWork();
    }

    /**
     * 4. Пример. thenAccept().
     * Допустим вы не хотите запускать отдельную задачу,
     * а хотите, чтобы просто было выполнено какое-то действие.
     * В отличие от thenRun(),
     * этот метод имеет доступ к результату CompletableFuture
     *
     * @throws Exception exception.
     */
    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко");
        bm.thenAccept((product) ->
                System.out.println("Сын: Я убрал " + product + " в холодильник "));
        iWork();
        System.out.println("Куплено: " + bm.get());
    }

    /**
     * 5. Пример thenApply().
     * Этот метод принимает Function. Также имеет доступ к результату.
     * Как раз благодаря этому,
     * мы можем произвести преобразование полученного результата.
     * Однако результат преобразования станет доступным только при вызове get().
     *
     * @throws Exception exception.
     */
    public static void thenApplyExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Молоко")
                .thenApply((product) -> "Сын: я налил тебе в кружку " + product + ". Держи.");
        iWork();
        System.out.println(bm.get());
    }

    /**
     * 6. Пример thenCompose()
     * Данный метод используется, если действия зависимы.
     * Т.е. сначала должно выполниться одно, а только потом другое.
     * Например, вам принципиально, чтобы сын сначала выбросил мусор,
     * а только потом сходил за молоком.
     * В ситуации можно записать так:
     *
     * @throws Exception exception.
     */
    public static void thenComposeExample() throws Exception {
        CompletableFuture<String> result = goToTrash().thenCompose(a -> buyProduct("Молоко"));
        iWork();
    }

    /**
     * 7. Пример. thenCombine().
     * Данный метод используется,
     * если действия могут быть выполнены независимо друг от друга.
     * Причем в качестве второго аргумента,
     * нужно передавать BiFunction – функцию,
     * которая преобразует результаты двух задач во что-то одно.
     * Например, первого сына вы посылаете выбросить мусор,
     * а второго сходить за молоком. В этой ситуации можно поступить так:
     *
     * @throws Exception exception.
     */
    public static void thenCombineExample() throws Exception {
        CompletableFuture<String> result = buyProduct("Молок")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }

    /**
     * 8. Пример. allOf()
     * то метод возвращает ComputableFuture<Void>,
     * при этом обеспечивает выполнение всех задач.
     * Например, вы зовете всех членов семью к столу.
     * Надо дождаться пока все помоют руки
     *
     * @param name String
     * @return Void.
     */
    public static CompletableFuture<Void> wasHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ", моет руки");
        });
    }

    /**
     * 8. Пример. allOf()
     * то метод возвращает ComputableFuture<Void>,
     * при этом обеспечивает выполнение всех задач.
     * Например, вы зовете всех членов семью к столу.
     * Надо дождаться пока все помоют руки
     *
     * @throws Exception exception.
     */
    public static void allOfExample() throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                wasHands("Папа"), wasHands("Мама"),
                wasHands("Ваня"), wasHands("Боря"));
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 9. Пример.anyOf()
     * Этот метод возвращает ComputableFuture<Object>.
     * Результатом будет первая выполненная задача.
     * На том же примере мы можем, например, узнать, кто сейчас моет руки.
     * Результаты запуск от запуска будут различаться.
     *
     * @param name String.
     * @return String.
     */
    public static CompletableFuture<String> whoWashHands(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + ", моет руки";
        });
    }

    /**
     * 9. Пример.anyOf()
     * Этот метод возвращает ComputableFuture<Object>.
     * Результатом будет первая выполненная задача.
     * На том же примере мы можем, например, узнать, кто сейчас моет руки.
     * Результаты запуск от запуска будут различаться.
     *
     * @throws Exception exception.
     */
    public static void anyOfExample() throws Exception {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                whoWashHands("Папа"), whoWashHands("Мама"),
                whoWashHands("Ваня"), whoWashHands("Боря"));
        System.out.println("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    public static void main(String[] args) throws Exception {
        anyOfExample();
        allOfExample();
        thenCombineExample();
        thenComposeExample();
        thenApplyExample();
        thenAcceptExample();
        thenRunExample();
        supplyAsyncExample();
        runAsyncExample();
    }
}
