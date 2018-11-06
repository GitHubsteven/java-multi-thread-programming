package parallel.design.parttern;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0 COPYRIGHT © 2001 - 2018 VOYAGE ONE GROUP INC. ALL RIGHTS RESERVED.
 * @Author jet.xie
 * @Description:
 * @Date: Created at 20:02 2018/11/5.
 */
public class FutureMode {
    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("name");
        System.out.println("future request has been send!");
        try {
            System.out.println("main thread sleep 3 second!");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //get result 放在最后，否则会是当前主线程进入阻塞/watting?
        System.out.println(data.getResult());

    }

    public static class RealData implements Data {
        private String result;

        RealData(String param) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(param);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
            result = sb.toString();
        }


        @Override
        public String getResult() {
            return result;
        }
    }

    public interface Data {
        String getResult();
    }


    public static class FutureData implements Data {
        protected RealData realData = null;
        protected boolean isReady = false;

        @Override
        public synchronized String getResult() {
            while (!isReady) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("=========InterruptedException");
                }
            }
            return realData.getResult();
        }

        public synchronized void setRealData(RealData realData) {
            if (isReady) {
                return;
            }

            this.realData = realData;
            isReady = true;
            notifyAll();
        }

    }

    public static class Client {
        public Data request(final String queryStr) {
            final FutureData future = new FutureData();
            new Thread(() -> {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }).start();
            return future;
        }
    }


}