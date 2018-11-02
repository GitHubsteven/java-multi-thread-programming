package chapter4.jobthreadpool;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: jet.xie
 * @Date: 2018/7/16
 * @Time: 17:30
 * @Description:
 * @version: 1.0.0
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个job
     *
     * @param job 待运行的任务
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加线程池数
     *
     * @param num 增加的数量
     */
    void addWorks(int num);

    /**
     * 减少线程
     * @param num 数量
     */
    void removeWorks(int num);

    /**
     * 得到正在执行的线程数量
     * @return 正在执行的线程数量
     */
    Integer getJobSize();
}