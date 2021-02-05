package org.pine.cloud.rx;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liujun
 * @sinace 2017/11/14 0014.
 */
public class RxJavaTest {
    private long startTime;
    private long endTime;

    @BeforeEach
    public void setup(){
        System.out.println("test start at: " + Instant.now());
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    public void teardown() {
        endTime = System.currentTimeMillis();
        System.out.println("test end at: " + Instant.now());
        System.out.println("spent time: " + (endTime-startTime) + " ms");
    }

    @Test
    public void testHello() {
        String[] names = new String[10000];
        for(int i=0; i<10000; i++){
            names[i] = "{code: " + i + ",description: Hello-" + (i+1) + "}";
        }
//        hello("Hello", "World", "!");
        hello(names);
    }

    public static void hello(String... names){
        Observable.from(names).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("same hello: " + s);
            }
        });
    }

    /**
     * 案例研究：异步任务的依赖
     * 假设我们的程序需要五个 micro-service 协作完成计算任务，这些 micro-services 之间存在数据依赖关系：
     *              client
     *              |  |  \
     *             fc  fd  fe
     *             |     \ |
     *             fa     fb
     */
    @Test
    public void testAsyncCompositeJoin() {
        System.out.println("Prepare for execution：Async Composite Join");
        long startTime = System.currentTimeMillis();

        // Tasks oa -> oc,  both in the same thread 1.
        Observable<String> oa = Observable.just("oa").observeOn(Schedulers.io()).flatMap(
                soa -> Observable.fromCallable(new TimeConsumingService("fa", 100, new String[]{}))
        );

        Observable<String> oc = oa.flatMap(
                (String res) -> {
                    System.out.println(res);
                    System.out.println("Executed At: " + (System.currentTimeMillis()-startTime) + " ms");
                    return Observable.fromCallable(new TimeConsumingService("fc", 200, new String[]{}));
                }
        );

        // Tasks ob -> (od,oe), ob, od, oe have special thread 2,3,4
        Observable<String> ob = Observable.just("ob").observeOn(Schedulers.io()).flatMap(
                sob -> Observable.fromCallable(new TimeConsumingService("fb", 2000, new String[]{}))
        );

        Observable<String> od_oe = ob.flatMap(
                (String res) -> {
                    System.out.println(res);
                    System.out.println("Executed At: " + (System.currentTimeMillis()-startTime) + " ms");
                    Observable<String> od = Observable.just("od").observeOn(Schedulers.io()).flatMap(
                            sod -> Observable.fromCallable(new TimeConsumingService("fd", 1000, new String[]{}))
                    );
                    Observable<String> oe = Observable.just("oe").observeOn(Schedulers.io()).flatMap(
                            soe -> Observable.fromCallable(new TimeConsumingService("fe",1000, new String[]{}))
                    );
                    return Observable.merge(od, oe);
                }
        );

        System.out.println("Observable build: " + (System.currentTimeMillis()-startTime) + "ms");

        // Tasks join oc, (od_oe) and subscribe
        Observable.merge(oc, od_oe).toBlocking().subscribe(
                (res) -> {
                    System.out.println(res);
                    System.out.println("Executed At: " + (System.currentTimeMillis()-startTime) + " ms");
                }
        );

        System.out.println("End Executed At: " + (System.currentTimeMillis()-startTime) + "ms");
    }

    @Test
    public void testRxJavaWithFlatMap() throws Exception {
        int count = 100000;
        ExecutorService es = Executors.newFixedThreadPool(200, new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
        URL url = new URL("http://10.200.4.140:8004/#/index");
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).subscribeOn(Schedulers.io()).flatMap(i -> {
                    System.out.println("A: " + Thread.currentThread().getName());
                    return Observable.just(i).subscribeOn(Schedulers.from(es)).map(v -> {
                                System.out.println("B: " + Thread.currentThread().getName());
                                try {
                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                    conn.setRequestMethod("GET");
                                    int responseCode = conn.getResponseCode();
                                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                    String inputLine;
                                    while ((inputLine = in.readLine()) != null) {
                                        //response.append(inputLine);
                                    }
                                    in.close();
                                    return responseCode;
                                } catch (Exception ex) {
                                    return -1;
                                }
                            }
                    );
                }
        ).observeOn(Schedulers.computation()).subscribe(statusCode -> {
            System.out.println("C: " + Thread.currentThread().getName());
        }, error -> {
        }, () -> {
            finishedLatch.countDown();
        });
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithFlatMap TPS: " + count * 1000 / t);
        es.shutdownNow();
    }

    @Test
    public void testRxJavaWithParallel() throws Exception {
        int count = 100000;
        ExecutorService es = Executors.newFixedThreadPool(200, new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
        URL url = new URL("http://10.200.4.140:8004/#/index");
        CountDownLatch finishedLatch = new CountDownLatch(count);
        long t = System.nanoTime();
        for (int k = 0; k < count; k++) {
            Observable.just(k).map(i -> {
                //System.out.println("A: " + Thread.currentThread().getName());
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        //response.append(inputLine);
                    }
                    in.close();
                    return responseCode;
                } catch (Exception ex) {
                    return -1;
                }
            }).subscribeOn(Schedulers.from(es)).observeOn(Schedulers.computation()).subscribe(statusCode -> {
            }, error -> {
            }, () -> {
                finishedLatch.countDown();
            });
        }
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithParallel TPS: " + count * 1000 / t);
        es.shutdownNow();
    }
}
